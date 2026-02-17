# Controller (Endpoints REST)

## O Que Colocar Aqui

Classes que definem os **endpoints HTTP** da API REST.

## Responsabilidades

- Receber requisicoes HTTP
- Validar dados de entrada (com Bean Validation)
- Converter DTOs para objetos de dominio
- Delegar para o Service
- Retornar respostas HTTP apropriadas

## O Que NAO Colocar

- Regras de negocio (vai no Service)
- Acesso direto ao banco (vai no Repository)
- Logica complexa de transformacao
- Try-catch excessivo (use @ControllerAdvice)

## Anotacoes Comuns

```java
@RestController      // Marca como controller REST
@RequestMapping      // Define path base
@GetMapping          // Endpoint GET
@PostMapping         // Endpoint POST
@PutMapping          // Endpoint PUT
@DeleteMapping       // Endpoint DELETE
@PatchMapping        // Endpoint PATCH
@PathVariable        // Parametro na URL
@RequestBody         // Corpo da requisicao
@RequestParam        // Query parameter
@Valid               // Ativa validacao do DTO
```

## Padroes de Resposta HTTP

| Operacao | Metodo | Sucesso | Erro |
|----------|--------|---------|------|
| Listar | GET | 200 OK | - |
| Buscar por ID | GET | 200 OK | 404 Not Found |
| Criar | POST | 201 Created | 400 Bad Request |
| Atualizar | PUT | 200 OK | 404 Not Found |
| Deletar | DELETE | 204 No Content | 404 Not Found |

## Boas Praticas

1. **Mantenha fino**: Controller nao deve ter logica de negocio
2. **Use DTOs**: Nunca exponha entidades diretamente
3. **Valide entrada**: Use @Valid nos DTOs
4. **Documente**: Use OpenAPI/Swagger
5. **Versionamento**: /api/v1/usuarios
