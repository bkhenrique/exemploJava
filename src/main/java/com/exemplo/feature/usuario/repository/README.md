# Repository (Acesso a Dados)

## O Que Colocar Aqui

Interfaces que definem o **acesso ao banco de dados**.

## Responsabilidades

- Definir metodos de acesso a dados
- Queries customizadas
- Operacoes CRUD
- Paginacao e ordenacao

## O Que NAO Colocar

- Regras de negocio (vai no Service)
- Logica de transformacao de dados
- Chamadas a APIs externas
- Validacoes de negocio

## Tipos de Repository

### 1. JpaRepository (mais comum)
```java
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Metodos CRUD ja inclusos
}
```

### 2. CrudRepository (mais simples)
```java
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    // CRUD basico sem paginacao
}
```

### 3. PagingAndSortingRepository
```java
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    // CRUD + paginacao
}
```

## Query Methods (Derivados do Nome)

```java
// Busca por campo
Optional<Usuario> findByEmail(String email);

// Busca com multiplos campos
List<Usuario> findByNomeAndStatus(String nome, StatusUsuario status);

// Verificacao de existencia
boolean existsByEmail(String email);

// Contagem
long countByStatus(StatusUsuario status);

// Ordenacao
List<Usuario> findByStatusOrderByNomeAsc(StatusUsuario status);

// Top/First
Optional<Usuario> findFirstByOrderByCriadoEmDesc();
```

## JPQL (@Query)

```java
@Query("SELECT u FROM Usuario u WHERE u.status = :status")
List<Usuario> buscarPorStatus(@Param("status") StatusUsuario status);

@Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
List<Usuario> buscarPorNomeContendo(@Param("nome") String nome);
```

## Native Query

```java
@Query(value = "SELECT * FROM usuarios WHERE status = ?1", nativeQuery = true)
List<Usuario> buscarPorStatusNativo(String status);
```

## Boas Praticas

1. **Nomeie claramente**: findByEmail, existsByEmail
2. **Use Optional**: Para buscas que podem nao retornar
3. **Paginacao**: Use Pageable para listas grandes
4. **Projections**: Use DTOs para queries otimizadas
5. **@Modifying**: Para UPDATE/DELETE queries
