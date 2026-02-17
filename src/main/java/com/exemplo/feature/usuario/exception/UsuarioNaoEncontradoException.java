package com.exemplo.feature.usuario.exception;

/**
 * Excecao lancada quando um Usuario nao e encontrado.
 * 
 * ONDE FICA: exception/ (dentro da feature)
 * 
 * QUANDO USAR:
 * - Busca por ID retorna vazio
 * - Operacao em usuario inexistente
 * 
 * HTTP STATUS: 404 Not Found
 */
public class UsuarioNaoEncontradoException extends RuntimeException {

    private final Long id;

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("Usuario nao encontrado com ID: %d", id));
        this.id = id;
    }

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
        this.id = null;
    }

    public Long getId() {
        return id;
    }
}
