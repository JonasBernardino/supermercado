package com.jb.supermercado.internal.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;


public record UsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotBlank(message = "O status é obrigatório")
        String status
) {
}