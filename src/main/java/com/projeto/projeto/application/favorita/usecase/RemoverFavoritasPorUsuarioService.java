package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverFavoritasPorUsuarioService implements RemoverFavoritasPorUsuarioUseCase {

    private final FavoritaGateway favoritaGateway;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public RemoverFavoritasPorUsuarioService(FavoritaGateway favoritaGateway,
                                             BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.favoritaGateway = favoritaGateway;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @Transactional
    public void execute(Long usuarioId) {
        buscarUsuarioPorIdUseCase.buscarUsuario(usuarioId);
        favoritaGateway.deleteByUsuarioId(usuarioId);
    }
}

