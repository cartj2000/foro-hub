package com.alura.foro_hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.alura.foro_hub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        try {
            //var algoritmo = Algorithm.HMAC256("12345678");
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("FORO_HUB alura.com")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(FechaExpiracion())
                    //.withClaim("id",usuario.getId())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el token JWT", exception);
            // Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    private Instant FechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    // --> comentariar method siguiente para ver el token:
    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    // specify any specific claim validations
                    .withIssuer("FORO_HUB alura.com")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw  new RuntimeException("Token JWT invalido o expirado!" + tokenJWT);
        }
    }

}
