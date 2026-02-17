package com.exemplo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Configuracao de CORS (Cross-Origin Resource Sharing).
 * 
 * ONDE FICA: common/config/
 * 
 * QUANDO USAR:
 * - API consumida por frontend em dominio diferente
 * - Desenvolvimento local (localhost diferentes)
 * 
 * CUIDADO:
 * - Nao use allowedOrigins("*") em producao
 * - Especifique apenas origens necessarias
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:4200"
        ));
        
        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        config.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Requested-With"
        ));
        
        config.setAllowCredentials(true);
        
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
