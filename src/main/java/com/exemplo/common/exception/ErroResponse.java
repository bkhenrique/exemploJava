package com.exemplo.common.exception;

import java.time.LocalDateTime;

/**
 * DTO padrao para respostas de erro.
 * 
 * ONDE FICA: common/exception/
 * 
 * USO: Retornado pelo GlobalExceptionHandler
 */
public class ErroResponse {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String path;

    public ErroResponse() {
    }

    public ErroResponse(LocalDateTime timestamp, int status, String erro, String mensagem, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
