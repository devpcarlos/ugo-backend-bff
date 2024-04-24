package com.ugo.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtUtils {
    @Value("${SECURITY.JWT.KEY}")
    private String SecretKey;
    @Value("${SECURITY.JWT.USER.GENERATOR}")
    private String Generator;
    public String CreateToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.SecretKey);
        String username = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));//READ,CREATE,DELETE,UPDATE
        String JwtToken = JWT.create()
                .withIssuer(this.Generator)
                .withSubject(username)
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+2600000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return JwtToken;
    }
    public DecodedJWT validateToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SecretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
         DecodedJWT decodedJWT = verifier.verify(token);
         return decodedJWT;
        }catch(JWTVerificationException e){
            throw new JWTVerificationException("Token invalid");
        }
    }
    public String extrackUserName(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }
    public Claim getExpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }
    public Map<String,Claim> getAll(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
