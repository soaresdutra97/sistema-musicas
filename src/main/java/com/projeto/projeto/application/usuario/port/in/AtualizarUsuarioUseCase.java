package com.projeto.projeto.application.usuario.port.in;

import com.projeto.projeto.application.usuario.dto.UsuarioRequest;
import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import com.projeto.projeto.domain.usuario.model.Usuario;

public interface AtualizarUsuarioUseCase {
    UsuarioResponse execute(Long id, UsuarioRequest usuarioRequest);
}
