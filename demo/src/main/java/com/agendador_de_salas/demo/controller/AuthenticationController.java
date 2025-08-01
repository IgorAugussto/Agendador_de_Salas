package com.agendador_de_salas.demo.controller;

import com.agendador_de_salas.demo.dto.AuthenticationDTO;
import com.agendador_de_salas.demo.dto.LoginResponseDTO;
import com.agendador_de_salas.demo.dto.RegisterDTO;
import com.agendador_de_salas.demo.entity.Users;
import com.agendador_de_salas.demo.repository.UserRepository;
import com.agendador_de_salas.demo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        logger.info("Tentativa de login com email: {}", data.email());
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            logger.debug("Autenticando usuário: {}", data.email());
            var auth = authenticationManager.authenticate(usernamePassword); // Deixa o Spring validar a senha
            logger.info("Autenticação bem-sucedida para: {}", data.email());
            var token = tokenService.generateToken((Users) auth.getPrincipal());
            logger.info("Autenticação bem-sucedida para: {}", data.email());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            logger.error("Falha na autenticação para email: {}. Erro: {}", data.email(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {

        logger.info("Tentativa de registro com email: {}", data.email());
        if (this.repository.findByEmail(data.email())!= null){
            logger.warn("Email já existe: {}", data.email());
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUsers = new Users(data.email(), encryptedPassword, data.role());
        this.repository.save(newUsers);
        logger.info("Usuário registrado com sucesso: {}", data.email());
        return ResponseEntity.ok("Usuário criado com sucesso. Seja bem-vindo!");
    }
}
