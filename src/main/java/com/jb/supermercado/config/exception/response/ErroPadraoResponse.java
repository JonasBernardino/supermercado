package com.jb.supermercado.config.exception.response;

public record ErroPadraoResponse(
        int status,
        String erro,
        String mensagem
) {
}