# Exception (Excecoes do Dominio)

## O Que Colocar Aqui

Excecoes **especificas do dominio/feature** para representar erros de negocio.

## Tipos de Excecao

### 1. Nao Encontrado
```java
UsuarioNaoEncontradoException
PedidoNaoEncontradoException
```

### 2. Regra de Negocio Violada
```java
EmailJaCadastradoException
SaldoInsuficienteException
PedidoJaFinalizadoException
```

### 3. Validacao
```java
DadosInvalidosException
CampoObrigatorioException
```

## Hierarquia Recomendada

```
RuntimeException (Java)
└── NegocioException (sua classe base)
    ├── EntidadeNaoEncontradaException
    ├── ValidacaoException
    └── OperacaoNaoPermitidaException
```

## Por Que Excecoes Customizadas

1. **Clareza**: Nome descreve o problema
2. **Tratamento especifico**: @ExceptionHandler por tipo
3. **Informacoes extras**: Campos como ID, campo invalido
4. **Codigo HTTP apropriado**: 404, 400, 409, etc.

## Estrutura de uma Excecao

```java
public class UsuarioNaoEncontradoException extends RuntimeException {
    
    private final Long id;
    
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario nao encontrado com ID: " + id);
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
}
```

## Mapeamento para HTTP

| Excecao | HTTP Status |
|---------|-------------|
| NaoEncontrado | 404 Not Found |
| JaExiste | 409 Conflict |
| Validacao | 400 Bad Request |
| NaoAutorizado | 401 Unauthorized |
| Proibido | 403 Forbidden |
| ErroInterno | 500 Internal Server Error |

## Tratamento Global

O tratamento global fica em `common/exception/GlobalExceptionHandler.java`:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleNaoEncontrado(
            UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(new ErroResponse(ex.getMessage()));
    }
}
```

## Boas Praticas

1. **Nomes descritivos**: UsuarioNaoEncontradoException
2. **Mensagens claras**: Inclua contexto (ID, campo)
3. **Unchecked**: Prefira RuntimeException
4. **Nao use para fluxo**: Excecoes sao para erros
