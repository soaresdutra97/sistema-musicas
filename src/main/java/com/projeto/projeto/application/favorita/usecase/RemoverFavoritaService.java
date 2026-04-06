package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverFavoritaService implements RemoverFavoritaUseCase {

    private final FavoritaGateway favoritaGateway;
    private final BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase;

    public RemoverFavoritaService(FavoritaGateway favoritaGateway,
                                  BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase) {
        this.favoritaGateway = favoritaGateway;
        this.buscarFavoritaPorIdUseCase = buscarFavoritaPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        favoritaGateway.delete(buscarFavoritaPorIdUseCase.buscarFavorita(id));
    }
}

