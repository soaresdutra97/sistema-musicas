package com.projeto.projeto.application.usuario.port.in;

import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;

public interface RemoverUsuarioUseCase {
    void execute(Long id);
}
