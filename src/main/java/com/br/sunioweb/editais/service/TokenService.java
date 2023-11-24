package com.br.sunioweb.editais.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.sunioweb.editais.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     *
     * @param user
     * @return Token valido com tempo de duração
     */
    public String generateToken(User user)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                                .withIssuer("editais-api") // Assinando a api
                                .withSubject(user.getUsername()) // destino do token
                                .withExpiresAt(genExpirationDate()) //tempo para expirar
                                .sign(algorithm);//assinatura e geração final
            return token;
        }catch (JWTCreationException JTWe)
        {
            throw new RuntimeException("error while gereration token: " + JTWe.getMessage());
        }
    }

    public  String validateToken(String token)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.require(algorithm)
                    .withIssuer("editais-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException JWTe){
            return "";
        }
    }

    /**
     *
     * @return Define o tempo de 2 horas para expirar o token
     */
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
