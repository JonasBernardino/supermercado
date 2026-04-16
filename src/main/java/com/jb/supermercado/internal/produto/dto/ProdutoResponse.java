package com.jb.supermercado.internal.produto.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao, // Adicionado
        BigDecimal preco,
        Integer quantidadeEstoque,
        String status // Adicionado
) {}