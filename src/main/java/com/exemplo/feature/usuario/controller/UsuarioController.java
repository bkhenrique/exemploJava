package com.exemplo.feature.usuario.controller;

import com.exemplo.feature.usuario.dto.CriarUsuarioRequest;
import com.exemplo.feature.usuario.dto.AtualizarUsuarioRequest;
import com.exemplo.feature.usuario.dto.UsuarioResponse;
import com.exemplo.feature.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operacoes de Usuario.
 * 
 * ONDE FICA: controller/
 * 
 * RESPONSABILIDADES:
 * - Definir endpoints HTTP
 * - Validar entrada com @Valid
 * - Delegar para UsuarioService
 * - Retornar DTOs de resposta
 */
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody CriarUsuarioRequest request) {
        
        UsuarioResponse usuario = usuarioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarUsuarioRequest request) {
        
        UsuarioResponse usuario = usuarioService.atualizar(id, request);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<UsuarioResponse> desativar(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.desativar(id);
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<UsuarioResponse> ativar(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.ativar(id);
        return ResponseEntity.ok(usuario);
    }
}
