package com.jb.supermercado.config.exception.response;

import java.util.Map;

public record ErroValidacaoResponse(
        int status,
        String erro,
        Map<String, String> campos
) {
}