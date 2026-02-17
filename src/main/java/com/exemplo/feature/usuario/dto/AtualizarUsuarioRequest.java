package com.exemplo.feature.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para atualizacao de Usuario.
 * 
 * ONDE FICA: dto/
 * 
 * TIPO: Request DTO (dados de entrada)
 * 
 * NOTA: Diferente do CriarUsuarioRequest,
 * aqui nao exigimos todos os campos.
 */
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    public AtualizarUsuarioRequest() {
    }

    public AtualizarUsuarioRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
