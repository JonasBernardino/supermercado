package com.jb.supermercado.internal.usuario.mapper;

import com.jb.supermercado.internal.usuario.dto.UsuarioRequest;
import com.jb.supermercado.internal.usuario.dto.UsuarioResponse;
import com.jb.supermercado.internal.usuario.entity.UsuarioEntity;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioEntity requestParaEntidade(UsuarioRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioRequest.nome());
        usuarioEntity.setEmail(usuarioRequest.email());
        usuarioEntity.setSenha(usuarioRequest.senha());
        usuarioEntity.setStatus(usuarioRequest.status());
        return usuarioEntity;
    }

    public static UsuarioResponse entidadeParaResponse(UsuarioEntity usuarioEntity) {
        return new UsuarioResponse(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getStatus());
    }

    public static List<UsuarioResponse> entidadeParaResponseRecordList(List<UsuarioEntity> usuarioEntityList) {
        return usuarioEntityList.stream()
                .map(UsuarioMapper::entidadeParaResponse)
                .collect(Collectors.toList());
    }
}