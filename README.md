# Estrutura de Projeto Java - Guia Didatico

Este projeto serve como **referencia/consulta** para entender onde cada componente deve ficar em uma arquitetura Java moderna baseada em **features/dominios**.

## Arquitetura Feature-Based (Domain-Driven)

```
src/main/java/com/exemplo/
├── feature/                    # Agrupa por funcionalidade/dominio
│   └── usuario/                # Exemplo: dominio Usuario
│       ├── controller/         # Endpoints REST
│       ├── service/            # Regras de negocio
│       ├── repository/         # Acesso a dados
│       ├── domain/             # Entidades e objetos de dominio
│       ├── dto/                # Objetos de transferencia
│       └── exception/          # Excecoes especificas do dominio
│
├── common/                     # Componentes compartilhados
│   ├── config/                 # Configuracoes globais
│   ├── exception/              # Excecoes globais e handlers
│   ├── security/               # Seguranca e autenticacao
│   └── util/                   # Utilitarios gerais
│
└── Application.java            # Classe principal
```

## Fluxo de uma Requisicao

```
Request HTTP
    │
    ▼
┌─────────────┐
│ Controller  │  ← Recebe request, valida entrada, delega para Service
└─────────────┘
    │
    ▼
┌─────────────┐
│  Service    │  ← Contem regras de negocio, orquestra operacoes
└─────────────┘
    │
    ▼
┌─────────────┐
│ Repository  │  ← Acessa banco de dados
└─────────────┘
    │
    ▼
┌─────────────┐
│   Domain    │  ← Entidades persistidas
└─────────────┘
```

## Como Usar Este Guia

Cada pasta contem:
- **README.md** - Explicacao didatica do que vai na pasta
- **Exemplo.java** - Codigo de exemplo pratico

## Indice Rapido

| Pasta | O que colocar |
|-------|---------------|
| `controller` | Endpoints REST, validacao de entrada |
| `service` | Regras de negocio, logica de aplicacao |
| `repository` | Queries, acesso a banco |
| `domain` | Entidades JPA, Value Objects |
| `dto` | Request/Response objects |
| `exception` | Excecoes customizadas |
| `config` | Beans, configuracoes |
| `security` | Autenticacao, autorizacao |
| `util` | Classes utilitarias |

---

> **Dica**: Quando tiver duvida, navegue ate a pasta e leia o README.md dela!
