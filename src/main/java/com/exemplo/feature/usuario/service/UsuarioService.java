package com.exemplo.feature.usuario.service;

import com.exemplo.feature.usuario.domain.Usuario;
import com.exemplo.feature.usuario.dto.AtualizarUsuarioRequest;
import com.exemplo.feature.usuario.dto.CriarUsuarioRequest;
import com.exemplo.feature.usuario.dto.UsuarioResponse;
import com.exemplo.feature.usuario.exception.UsuarioNaoEncontradoException;
import com.exemplo.feature.usuario.exception.EmailJaCadastradoException;
import com.exemplo.feature.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service que contem as regras de negocio de Usuario.
 * 
 * ONDE FICA: service/
 * 
 * RESPONSABILIDADES:
 * - Regras de negocio
 * - Orquestracao de operacoes
 * - Transacoes
 * - Conversao DTO <-> Entidade
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = buscarOuFalhar(id);
        return toResponse(usuario);
    }

    @Transactional
    public UsuarioResponse criar(CriarUsuarioRequest request) {
        validarEmailUnico(request.getEmail());
        
        Usuario usuario = new Usuario(
                request.getNome(),
                request.getEmail(),
                request.getSenha()
        );
        
        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, AtualizarUsuarioRequest request) {
        Usuario usuario = buscarOuFalhar(id);
        
        usuario.setNome(request.getNome());
        
        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarOuFalhar(id);
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public UsuarioResponse desativar(Long id) {
        Usuario usuario = buscarOuFalhar(id);
        usuario.desativar();
        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    @Transactional
    public UsuarioResponse ativar(Long id) {
        Usuario usuario = buscarOuFalhar(id);
        usuario.ativar();
        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    private Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private void validarEmailUnico(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException(email);
        }
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getStatus().name(),
                usuario.getCriadoEm()
        );
    }
}
