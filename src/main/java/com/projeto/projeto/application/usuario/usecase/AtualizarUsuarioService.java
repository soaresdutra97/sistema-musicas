package com.projeto.projeto.application.usuario.usecase;

import com.projeto.projeto.application.usuario.port.in.*;


import com.projeto.projeto.application.usuario.dto.UsuarioRequest;
import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import com.projeto.projeto.domain.usuario.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class AtualizarUsuarioService implements AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final UsuarioMapper usuarioMapper;

    public AtualizarUsuarioService(UsuarioGateway usuarioGateway,
                                   BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                                   UsuarioMapper usuarioMapper) {
        this.usuarioGateway = usuarioGateway;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioResponse execute(Long id, UsuarioRequest usuarioRequest) {
        Usuario usuario = buscarUsuarioPorIdUseCase.buscarUsuario(id);

        if (!usuario.getEmail().equals(usuarioRequest.email()) && usuarioGateway.existsByEmail(usuarioRequest.email())) {
            throw new RuntimeException("Ja existe um usuario cadastrado com este email.");
        }

        usuarioMapper.converterDTOParaEntity(usuario, usuarioRequest);

        return usuarioMapper.converterEntityParaDTO(usuarioGateway.save(usuario));
    }
}



