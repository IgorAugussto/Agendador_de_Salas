package com.agendador_de_salas.demo.infra.security;

import com.agendador_de_salas.demo.entity.Users;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    //Chave para guardar a senha
    @Value("${api.security.token.secret}")
    private String secret;

    //Token vai transitar entre cliente e servidor para informar qual a role do usuario em questão
    public String generateToken(Users users){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")//Quem criou o token
                    .withSubject(users.getEmail())//Usuario que está recebendo esse token
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);//fazer a assinatura final
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    //Validação do token criado anteriormente
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
