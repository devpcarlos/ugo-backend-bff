package com.ugo.Config;

import com.ugo.JWT.JwtAuthFilter;
import com.ugo.JWT.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Clase de configuración para la creación de Beans a utilizar
 */
@Configuration
@RequiredArgsConstructor
public class AplicationConfig {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    /**
     * Bean de JwtAuthFilter para inyeccion
     * @return Implementación JwtAuthFilter
     */
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtAuthenticationProvider);
    }
}
