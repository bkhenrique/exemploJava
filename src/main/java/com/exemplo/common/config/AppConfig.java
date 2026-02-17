package com.exemplo.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuracoes gerais da aplicacao.
 * 
 * ONDE FICA: common/config/
 * 
 * RESPONSABILIDADES:
 * - Definir beans globais
 * - Configurar bibliotecas de terceiros
 * - Customizar comportamentos padrao
 */
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
