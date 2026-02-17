# Exception Global (Handler e Excecoes Base)

## O Que Colocar Aqui

- **GlobalExceptionHandler**: Trata todas as excecoes da API
- **Excecoes base**: Classes abstratas/base para excecoes
- **DTOs de erro**: Formato padrao de resposta de erro

## GlobalExceptionHandler

O `@RestControllerAdvice` captura excecoes lancadas nos controllers e retorna respostas HTTP apropriadas.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handle404(...) {
        return ResponseEntity.status(404).body(...);
    }
}
```

## Excecoes Base Recomendadas

```
NegocioException (base)
├── EntidadeNaoEncontradaException
├── ValidacaoException
├── OperacaoNaoPermitidaException
└── ConflitoDeDadosException
```

## Formato Padrao de Erro

```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 404,
    "erro": "Entidade nao encontrada",
    "mensagem": "Usuario nao encontrado com ID: 123",
    "path": "/api/v1/usuarios/123"
}
```

## Mapeamento Excecao -> HTTP

| Excecao | HTTP | Quando |
|---------|------|--------|
| `MethodArgumentNotValidException` | 400 | @Valid falhou |
| `HttpMessageNotReadableException` | 400 | JSON malformado |
| `EntidadeNaoEncontradaException` | 404 | Busca sem resultado |
| `ConflitoDeDadosException` | 409 | Dado duplicado |
| `AccessDeniedException` | 403 | Sem permissao |
| `Exception` | 500 | Erro nao tratado |

## Boas Praticas

1. **Capture especifico primeiro**: Handlers mais especificos antes
2. **Log de erros 500**: Sempre logue erros internos
3. **Nao exponha stack trace**: Em producao, oculte detalhes
4. **Formato consistente**: Use sempre o mesmo DTO de erro
5. **Mensagens claras**: Usuario deve entender o erro
