package com.jb.supermercado.internal.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestRecord(
        @NotBlank(message = "O campo nome e obrigatorio")
        String nome,
        String email,
        String senha) {
}
