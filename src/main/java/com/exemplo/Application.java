package com.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicacao Spring Boot.
 * 
 * ONDE FICA: Na raiz do pacote base (com.exemplo)
 * 
 * O QUE FAZ:
 * - Ponto de entrada da aplicacao
 * - Habilita auto-configuracao do Spring Boot
 * - Escaneia componentes a partir deste pacote
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
