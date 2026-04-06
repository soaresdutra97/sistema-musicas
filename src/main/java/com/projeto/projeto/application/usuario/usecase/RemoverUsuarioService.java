package com.projeto.projeto.application.usuario.usecase;

import com.projeto.projeto.application.usuario.port.in.*;


import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverUsuarioService implements RemoverUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public RemoverUsuarioService(UsuarioGateway usuarioGateway,
                                 BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.usuarioGateway = usuarioGateway;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        usuarioGateway.delete(buscarUsuarioPorIdUseCase.buscarUsuario(id));
    }
}

