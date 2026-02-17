# Service (Regras de Negocio)

## O Que Colocar Aqui

Classes que contem a **logica de negocio** e orquestram as operacoes.

## Responsabilidades

- Implementar regras de negocio
- Orquestrar chamadas a repositories
- Coordenar transacoes
- Validacoes de negocio
- Conversao entre DTOs e Entidades
- Chamar outros services quando necessario

## O Que NAO Colocar

- Anotacoes de HTTP/REST (vai no Controller)
- SQL ou queries diretas (vai no Repository)
- Logica de apresentacao
- Acesso direto a HttpServletRequest/Response

## Anotacoes Comuns

```java
@Service           // Marca como componente de servico
@Transactional     // Gerencia transacao do banco
@Validated         // Habilita validacao de metodos
```

## Estrutura Tipica de um Service

```java
@Service
public class UsuarioService {
    
    private final UsuarioRepository repository;
    
    // 1. Injecao via construtor (preferivel)
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    
    // 2. Metodo transacional para escrita
    @Transactional
    public Usuario criar(CriarUsuarioRequest request) {
        // Validacao de negocio
        // Criacao da entidade
        // Persistencia
        // Retorno
    }
    
    // 3. Metodo somente leitura
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        // Busca e retorno
    }
}
```

## Boas Praticas

1. **Uma responsabilidade**: Cada service foca em um dominio
2. **Transacoes**: Use @Transactional apropriadamente
3. **Injecao por construtor**: Facilita testes
4. **Nao retorne entidades**: Converta para DTOs
5. **Validacao de negocio**: Aqui, nao no controller
6. **Logging**: Registre operacoes importantes

## Quando Criar um Novo Service

- Novo dominio/feature
- Logica compartilhada entre controllers
- Operacoes que envolvem multiplos repositories
