package com.exemplo.feature.usuario.domain;

/**
 * Enum que representa os possiveis status de um Usuario.
 * 
 * ONDE FICA: domain/ (junto com a entidade que usa)
 * 
 * QUANDO USAR ENUM:
 * - Conjunto fixo e pequeno de valores
 * - Valores definidos em tempo de compilacao
 * - Usado em logica de negocio
 */
public enum StatusUsuario {
    
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PENDENTE("Pendente de Ativacao"),
    BLOQUEADO("Bloqueado");

    private final String descricao;

    StatusUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
