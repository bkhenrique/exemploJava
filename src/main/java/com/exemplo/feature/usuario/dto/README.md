# DTO (Data Transfer Objects)

## O Que Colocar Aqui

Objetos usados para **transferir dados** entre camadas, especialmente entre Controller e Service.

## Tipos de DTO

### 1. Request DTOs
Dados que **entram** na API (corpo de POST/PUT)
```
CriarUsuarioRequest
AtualizarUsuarioRequest
LoginRequest
```

### 2. Response DTOs
Dados que **saem** da API (resposta ao cliente)
```
UsuarioResponse
TokenResponse
ErroResponse
```

### 3. Filter/Search DTOs
Criterios de busca e filtros
```
UsuarioFiltro
PaginacaoRequest
```

## Por Que Usar DTOs

1. **Seguranca**: Nao expoe campos sensiveis (senha, tokens)
2. **Flexibilidade**: API independente da estrutura do banco
3. **Validacao**: Anotacoes de validacao no DTO
4. **Versionamento**: Facilita evolucao da API

## Anotacoes de Validacao (Bean Validation)

```java
@NotNull          // Campo obrigatorio
@NotBlank         // String nao vazia
@NotEmpty         // Colecao nao vazia
@Size(min, max)   // Tamanho de string/colecao
@Min / @Max       // Valores numericos
@Email            // Formato de email
@Pattern          // Regex customizado
@Past / @Future   // Validacao de datas
@Valid            // Validacao em cascata
```

## Estrutura Comum de Request

```java
public class CriarUsuarioRequest {
    
    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 2, max = 100)
    private String nome;
    
    @NotBlank
    @Email(message = "Email invalido")
    private String email;
    
    // getters e setters ou records
}
```

## Records vs Classes

### Usando Record (Java 14+, imutavel)
```java
public record UsuarioResponse(
    Long id,
    String nome,
    String email
) {}
```

### Usando Classe (mutavel, mais controle)
```java
public class UsuarioResponse {
    private Long id;
    private String nome;
    // getters, setters, construtor
}
```

## Boas Praticas

1. **Nomes claros**: CriarXxxRequest, XxxResponse
2. **Imutabilidade**: Prefira records ou campos final
3. **Validacao**: Sempre valide requests
4. **Nao reutilize**: Crie DTOs especificos por caso de uso
5. **Documente**: Use @Schema do OpenAPI
