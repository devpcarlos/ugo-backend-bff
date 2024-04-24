package com.ugo.Config.Filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ugo.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidation extends OncePerRequestFilter {

    public JwtTokenValidation(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String JwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(JwtToken != null){
            JwtToken = JwtToken.substring(7);
            DecodedJWT decodedJWT = jwtUtils.validateToken(JwtToken);
            String username = jwtUtils.extrackUserName(decodedJWT);
            String stringAuthorities = jwtUtils.getExpecificClaim(decodedJWT,"authoritiess").asString();

            Collection<? extends GrantedAuthority>authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }
        filterChain.doFilter(request,response);
    }

}
