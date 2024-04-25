package com.ugo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class JwtCommandLine {

    private static final String SECRET = "EL-SECRETO-DE-CARLOS-QUE-TIENE-QUE-SER-UNA-CADENA-LARGA";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static String generateJwtToken() {
        Instant now = Instant.now();
        return Jwts.builder()
                .claim("name", "Carlos Barrera")
                .claim("email", "carlos@ugo.com")
                .subject("Ugo App Login")
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .signWith(KEY)
                .compact();
    }

    private static void printClaims(Jws<Claims> claimsJws) {
        System.out.println("CABECERA DEL TOKEN: " + claimsJws.getHeader());
        System.out.println("PAYLOAD DEL TOKEN: " + claimsJws.getPayload());
        System.out.println("SUBJECT DEL TOKEN: " + claimsJws.getPayload().getSubject());
        System.out.println("ID DEL TOKEN: " + claimsJws.getPayload().getId());
        System.out.println("FECHA DE CREACION DEL TOKEN: " + claimsJws.getPayload().getIssuedAt());
        System.out.println("FECHA DE EXPIRACION TOKEN: " + claimsJws.getPayload().getExpiration());
    }

    private static void validateAndParseJwtToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().verifyWith(KEY).build().parseSignedClaims(jwtToken);
            System.out.println("INFO: JWT confiable. La firma del token es legítima.");
            printClaims(claimsJws);
        } catch (JwtException e) {
            System.err.println("ERROR: Token JWT NO confiable. Falló la verificación de la firma.");
        }
    }

    public static void main(String[] args) {
        String jwtToken = generateJwtToken();
        System.out.println("JWT GENERADO: " + jwtToken);
        validateAndParseJwtToken(jwtToken);
    }
}
