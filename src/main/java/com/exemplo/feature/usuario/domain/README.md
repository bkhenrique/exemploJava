# Domain (Entidades)

## O Que Colocar Aqui

Entidades JPA e objetos de dominio que representam os **conceitos centrais do negocio**.

## Responsabilidades

- Representar tabelas do banco de dados (Entidades JPA)
- Conter logica de negocio **intrinseca** ao objeto
- Definir relacionamentos entre entidades
- Value Objects (objetos imutaveis sem identidade)

## O Que NAO Colocar

- Logica de orquestracao (vai no Service)
- Validacoes de entrada (vai no DTO ou Controller)
- Queries complexas (vai no Repository)
- Formatacao para API (vai no DTO)

## Anotacoes Comuns

```java
@Entity           // Marca como entidade JPA
@Table            // Configura nome da tabela
@Id               // Chave primaria
@GeneratedValue   // Geracao automatica de ID
@Column           // Configuracao de coluna
@OneToMany        // Relacionamento 1:N
@ManyToOne        // Relacionamento N:1
@ManyToMany       // Relacionamento N:N
@Embedded         // Value Object embutido
@Embeddable       // Marca classe como Value Object
```

## Boas Praticas

1. **Encapsulamento**: Use getters/setters ou Lombok
2. **Equals/HashCode**: Baseie no ID ou campos de negocio
3. **Construtor protegido**: JPA precisa de construtor sem args
4. **Validacoes de dominio**: Regras que sempre devem valer

## Quando Usar Value Objects

Use para conceitos que nao tem identidade propria:
- Endereco
- CPF
- Email
- Periodo de datas
- Dinheiro/Moeda
