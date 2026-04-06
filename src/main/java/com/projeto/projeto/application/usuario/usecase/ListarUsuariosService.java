package com.projeto.projeto.application.usuario.usecase;

import com.projeto.projeto.application.usuario.port.in.*;


import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarUsuariosService implements ListarUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;
    private final UsuarioMapper usuarioMapper;

    public ListarUsuariosService(UsuarioGateway usuarioGateway, UsuarioMapper usuarioMapper) {
        this.usuarioGateway = usuarioGateway;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public List<UsuarioResponse> execute() {
        return usuarioGateway.findAll().stream().map(usuarioMapper::converterEntityParaDTO).toList();
    }
}


