package com.agendador_de_salas.demo.infra.security;

import com.agendador_de_salas.demo.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        if (requestPath.startsWith("/api/auth/")) {
            logger.info("Ignorando autenticação para: {}", requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        if (token != null) {
            logger.debug("Validando token: {}", token);
            try {
                var login = tokenService.validateToken(token);
                logger.debug("Email extraído do token: {}", login);
                if (!login.isEmpty()) {
                    UserDetails user = userRepository.findByEmail(login);
                    if (user != null) {
                        logger.info("Usuário encontrado: {}", user.getUsername());
                        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        logger.warn("Usuário não encontrado para email: {}", login);
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                } else {
                    logger.warn("Token inválido ou expirado");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            } catch(Exception e) {
                logger.error("Erro ao validar token: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
