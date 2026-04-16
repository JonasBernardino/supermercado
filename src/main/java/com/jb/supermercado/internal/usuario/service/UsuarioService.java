package com.jb.supermercado.internal.usuario.service;

import com.jb.supermercado.config.exception.BusinessException;
import com.jb.supermercado.config.exception.RecursoNaoEncontradoException;
import com.jb.supermercado.internal.usuario.dto.UsuarioRequest;
import com.jb.supermercado.internal.usuario.dto.UsuarioResponse;
import com.jb.supermercado.internal.usuario.entity.UsuarioEntity;
import com.jb.supermercado.internal.usuario.mapper.UsuarioMapper;
import com.jb.supermercado.internal.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> listaUsuarios() {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
        return UsuarioMapper.entidadeParaResponseRecordList(usuarios);
    }

    public void cadastrarUsuario(UsuarioRequest usuarioRequest) {
        boolean emailJaExiste = this.usuarioRepository.existsByEmail(usuarioRequest.email());
        if (emailJaExiste) {
            throw new BusinessException("Já existe um usuário cadastrado com este e-mail");
        }
        UsuarioEntity usuarioEntity = UsuarioMapper.requestParaEntidade(usuarioRequest);
        this.usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponse buscarUsuarioPorId(Long id) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Usuário não encontrado"));
        return UsuarioMapper.entidadeParaResponse(usuarioEntity);
    }

    public void atualizarUsuario(Long id, UsuarioRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id).orElseThrow(()
                -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        usuarioEntity.setNome(usuarioRequest.nome());
        usuarioEntity.setEmail(usuarioRequest.email());
        usuarioEntity.setSenha(usuarioRequest.senha());
        usuarioEntity.setStatus(usuarioRequest.status()); // Adicionado

        this.usuarioRepository.save(usuarioEntity);
    }

    public void removerUsuario(Long id) {
        if (!this.usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado para exclusão");
        }
        this.usuarioRepository.deleteById(id);
    }
}
