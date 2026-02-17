package com.exemplo.feature.usuario.dto;

import java.time.LocalDateTime;

/**
 * DTO de resposta com dados do Usuario.
 * 
 * ONDE FICA: dto/
 * 
 * TIPO: Response DTO (dados de saida)
 * 
 * NOTA:
 * - NAO inclui senha (seguranca)
 * - Campos formatados para a API
 * - Pode ser um Record em Java 14+
 */
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String status;
    private LocalDateTime criadoEm;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String nome, String email, String status, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
