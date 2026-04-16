package com.jb.supermercado.internal.usuario.dto;


public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String status
) {}