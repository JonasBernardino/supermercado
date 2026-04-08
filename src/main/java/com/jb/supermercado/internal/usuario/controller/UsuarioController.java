package com.jb.supermercado.internal.usuario.controller;

import com.jb.supermercado.internal.usuario.dto.UsuarioRequestRecord;
import com.jb.supermercado.internal.usuario.dto.UsuarioResponseRecord;
import com.jb.supermercado.internal.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseRecord>> listar() {
        return ResponseEntity.ok(this.usuarioService.listaUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseRecord> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(this.usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioRequestRecord usuarioRequest) {
        this.usuarioService.cadastrarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestRecord usuarioRequest) {
        this.usuarioService.atualizarUsuario(id, usuarioRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
