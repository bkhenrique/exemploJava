package com.exemplo.feature.usuario.service;

import com.exemplo.feature.usuario.domain.StatusUsuario;
import com.exemplo.feature.usuario.domain.Usuario;
import com.exemplo.feature.usuario.dto.CriarUsuarioRequest;
import com.exemplo.feature.usuario.dto.UsuarioResponse;
import com.exemplo.feature.usuario.exception.EmailJaCadastradoException;
import com.exemplo.feature.usuario.exception.UsuarioNaoEncontradoException;
import com.exemplo.feature.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Teste unitario do UsuarioService.
 * 
 * ONDE FICA: src/test/java/ (espelhando a estrutura de src/main/java)
 * 
 * TIPO: Teste unitario com mocks
 * 
 * PADRAO: AAA (Arrange-Act-Assert)
 */
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private CriarUsuarioRequest criarRequest;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Joao Silva", "joao@email.com", "senha123");
        criarRequest = new CriarUsuarioRequest("Joao Silva", "joao@email.com", "senha123");
    }

    @Test
    @DisplayName("Deve criar usuario quando dados validos")
    void deveCriarUsuario_quandoDadosValidos() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponse response = usuarioService.criar(criarRequest);

        assertNotNull(response);
        assertEquals("Joao Silva", response.getNome());
        assertEquals("joao@email.com", response.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lancar excecao quando email ja cadastrado")
    void deveLancarExcecao_quandoEmailJaCadastrado() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(EmailJaCadastradoException.class, () -> {
            usuarioService.criar(criarRequest);
        });

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve buscar usuario por ID quando existir")
    void deveBuscarUsuario_quandoExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponse response = usuarioService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals("Joao Silva", response.getNome());
    }

    @Test
    @DisplayName("Deve lancar excecao quando usuario nao encontrado")
    void deveLancarExcecao_quandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorId(999L);
        });
    }

    @Test
    @DisplayName("Deve desativar usuario quando ativo")
    void deveDesativarUsuario_quandoAtivo() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponse response = usuarioService.desativar(1L);

        assertNotNull(response);
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
