package com.exemplo.feature.usuario.exception;

/**
 * Excecao lancada quando tentam cadastrar um email que ja existe.
 * 
 * ONDE FICA: exception/ (dentro da feature)
 * 
 * QUANDO USAR:
 * - Criacao de usuario com email duplicado
 * - Atualizacao de email para um ja existente
 * 
 * HTTP STATUS: 409 Conflict
 */
public class EmailJaCadastradoException extends RuntimeException {

    private final String email;

    public EmailJaCadastradoException(String email) {
        super(String.format("Email ja cadastrado: %s", email));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
