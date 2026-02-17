package com.exemplo.feature.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para criacao de um novo Usuario.
 * 
 * ONDE FICA: dto/
 * 
 * TIPO: Request DTO (dados de entrada)
 * 
 * RESPONSABILIDADES:
 * - Representar dados de entrada
 * - Validar campos com Bean Validation
 * - Desacoplar API da entidade
 */
public class CriarUsuarioRequest {

    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email deve ser valido")
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
    private String senha;

    public CriarUsuarioRequest() {
    }

    public CriarUsuarioRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
