package com.ugo.Config;

import com.ugo.JWT.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                // Deshabilita la protección CSRF (Cross-Site Request Forgery) para el uso de JWT
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers("/auth/**","/auth/ogin" ).permitAll()
                                .requestMatchers("/user/create").permitAll()
                                .anyRequest().authenticated()
                )
                // Configura la gestión de sesiones, en este caso la aplicación no creará ni utilizará sesiones HTTP
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Añade el filtro que hemos creado para ejecutarse antes del filtro predeterminado
                . addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(withDefaults());

        return  http.build();

    }

}

