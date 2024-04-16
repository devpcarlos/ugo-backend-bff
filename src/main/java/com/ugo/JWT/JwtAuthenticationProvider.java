package com.ugo.JWT;

import com.auth0.jwt.JWT;
import com.ugo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.AlgorithmParameters;
import java.util.Date;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class JwtAuthenticationProvider {
    /**
     * Llave para cifrar el jwt
     */
    @Value("${jwt.secret.key}")
    private String SecretKey;
    /**
     * Lista blanca con los jwt creados
     */
    private HashMap<String, UserDto> listToken = new HashMap<>();
    /**
     * Crea un nuevo jwt en base al cliente recibido por parametro y lo agrega a la lista blanca
     * @param UserJwt Cliente a utilizar en la creacion del jwt
     * @return Jwt creado
     */
    public String createToken(UserDto UserJwt) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(SecretKey);

        String tokenCreated = JWT.create()
                .withClaim("FirstName", UserJwt.getNombre())
                .withClaim("LastName",UserJwt.getApellidoPaterno())
                .withClaim("email", UserJwt.getEmail())
                .withClaim("rol", UserJwt.getRoleId())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        listToken.put(tokenCreated, UserJwt);
        return tokenCreated;
    }

    /**
     * Valida si el token es valido y retorna una sesión del usuario
     // @param token Token a validar
     * @return Sesion del usuario
     // @throws CredentialsExpiredException Si el token ya expiró
     // @throws BadCredentialsException Si el token no existe en la lista blanca
     */
    public Authentication validateToken(String token) throws AuthenticationException {

        System.out.println("entre tambien aqui");
        System.out.println(token);

        //verifica el token como su firma y expiración, lanza una excepcion si algo falla
        JWT.require(Algorithm.HMAC256(SecretKey)).build().verify(token);


        UserDto exists = listToken.get(token);
        if (exists == null) {
            throw new BadCredentialsException("Usuario no registrado.");
        }

        //return new UsernamePasswordAuthenticationToken(userTest, token, userTest.getAuthorities());

        //return new UsernamePasswordAuthenticationToken(userTest, token, Collections.singletonList(new SimpleGrantedAuthority("WRITE_PRIVILEGE")));

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_"+exists.getRoleId())); //rol
        // rolesAndAuthorities.add(new SimpleGrantedAuthority("ELIMINAR_PRIVILEGE")); // permisos del rol


        return new UsernamePasswordAuthenticationToken(exists, token, rolesAndAuthorities);
    }

    public String deleteToken(String jwt) {

        if (!listToken.containsKey(jwt)) {
            return "No existe token";
        }

        listToken.remove(jwt);
        return "Sesión cerrada exitosamente";
    }

}
