package com.exemplo.common.exception;

import com.exemplo.feature.usuario.exception.EmailJaCadastradoException;
import com.exemplo.feature.usuario.exception.UsuarioNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler global de excecoes da API.
 * 
 * ONDE FICA: common/exception/
 * 
 * RESPONSABILIDADES:
 * - Capturar excecoes lancadas nos controllers
 * - Converter para respostas HTTP apropriadas
 * - Padronizar formato de erro
 * - Logar erros internos
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException ex,
            WebRequest request) {
        
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Entidade nao encontrada",
                ex.getMessage(),
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErroResponse> handleEmailJaCadastrado(
            EmailJaCadastradoException ex,
            WebRequest request) {
        
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de dados",
                ex.getMessage(),
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidacao(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> erros = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        
        ErroValidacaoResponse erro = new ErroValidacaoResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validacao",
                "Um ou mais campos estao invalidos",
                erros
        );
        
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenerico(
            Exception ex,
            WebRequest request) {
        
        log.error("Erro interno nao tratado", ex);
        
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
