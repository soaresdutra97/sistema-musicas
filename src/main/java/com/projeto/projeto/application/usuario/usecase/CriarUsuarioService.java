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
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper usuarioMapper;

    public CriarUsuarioService(UsuarioGateway usuarioGateway, UsuarioMapper usuarioMapper) {
        this.usuarioGateway = usuarioGateway;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioResponse execute(UsuarioRequest usuarioRequest) {
        if (usuarioGateway.existsByEmail(usuarioRequest.email())) {
            throw new RuntimeException("Ja existe um usuario cadastrado com este email.");
        }

        Usuario usuario = new Usuario();
        usuarioMapper.converterDTOParaEntity(usuario, usuarioRequest);

        return usuarioMapper.converterEntityParaDTO(usuarioGateway.save(usuario));
    }
}



