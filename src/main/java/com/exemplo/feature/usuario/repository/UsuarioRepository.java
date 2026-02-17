package com.exemplo.feature.usuario.repository;

import com.exemplo.feature.usuario.domain.StatusUsuario;
import com.exemplo.feature.usuario.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para acesso aos dados de Usuario.
 * 
 * ONDE FICA: repository/
 * 
 * RESPONSABILIDADES:
 * - Definir metodos de acesso ao banco
 * - Queries customizadas
 * - CRUD operations
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByStatus(StatusUsuario status);

    Page<Usuario> findByStatus(StatusUsuario status, Pageable pageable);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    long countByStatus(StatusUsuario status);

    @Query("SELECT u FROM Usuario u WHERE u.status = :status ORDER BY u.criadoEm DESC")
    List<Usuario> buscarPorStatusOrdenadoPorData(@Param("status") StatusUsuario status);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Usuario> buscarPorTermo(@Param("termo") String termo);

    @Modifying
    @Query("UPDATE Usuario u SET u.status = :status WHERE u.id = :id")
    int atualizarStatus(@Param("id") Long id, @Param("status") StatusUsuario status);
}
