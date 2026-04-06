package com.projeto.projeto.application.usuario.usecase;

import com.projeto.projeto.application.usuario.port.in.*;


import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import com.projeto.projeto.domain.usuario.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarUsuarioPorIdService implements BuscarUsuarioPorIdUseCase {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper usuarioMapper;

    public BuscarUsuarioPorIdService(UsuarioGateway usuarioGateway, UsuarioMapper usuarioMapper) {
        this.usuarioGateway = usuarioGateway;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    public UsuarioResponse execute(Long id) {
        return usuarioMapper.converterEntityParaDTO(buscarUsuario(id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuario(Long id) {
        return usuarioGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Usuario not found with id: " + id));
    }
}



