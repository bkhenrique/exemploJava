package com.exemplo.feature.usuario.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa um Usuario no sistema.
 * 
 * ONDE FICA: domain/
 * 
 * RESPONSABILIDADES:
 * - Mapear para tabela do banco
 * - Conter regras de negocio intrinsecas ao Usuario
 * - Relacionamentos com outras entidades
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusUsuario status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    protected Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = StatusUsuario.ATIVO;
        this.criadoEm = LocalDateTime.now();
    }

    public void desativar() {
        if (this.status == StatusUsuario.INATIVO) {
            throw new IllegalStateException("Usuario ja esta inativo");
        }
        this.status = StatusUsuario.INATIVO;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void ativar() {
        this.status = StatusUsuario.ATIVO;
        this.atualizadoEm = LocalDateTime.now();
    }

    public boolean isAtivo() {
        return this.status == StatusUsuario.ATIVO;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.atualizadoEm = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        this.atualizadoEm = LocalDateTime.now();
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}
