package com.ugo.JWT;

import com.ugo.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    /**
     * Lista blanca de URIs
     */
    private List<String> urlsToSkip = List.of("/auth");

    /**
     * Verifica si a la URI no se le debe aplicar el filtro
     // @param request current HTTP request Petición a validar
     * @return True la URI existe en la lista blanca, false de lo contrario
     // @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("en esta peticion se rompe");
        System.out.println(request.getRequestURI());
        return urlsToSkip.stream().anyMatch(url -> request.getRequestURI().contains(url));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);


        if (header == null) {
            throw new UnauthorizedException();
        }

        String[] authElements = header.split(" ");

        if (authElements.length != 2 || !"Bearer".equals(authElements[0])) {
            throw new UnauthorizedException();
        }

        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(authElements[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("voy a imprimir el context");
            System.out.println(SecurityContextHolder.getContext());
            System.out.println("voy a imprimir la autenticacion");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            System.out.println("se estalló");
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("llegué aqui");

        filterChain.doFilter(request, response);
    }


}
