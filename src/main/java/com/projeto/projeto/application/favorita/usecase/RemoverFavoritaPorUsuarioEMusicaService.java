package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.musica.port.in.BuscarMusicaPorIdUseCase;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import com.projeto.projeto.domain.favorita.model.Favorita;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverFavoritaPorUsuarioEMusicaService implements RemoverFavoritaPorUsuarioEMusicaUseCase {

    private final FavoritaGateway favoritaGateway;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase;

    public RemoverFavoritaPorUsuarioEMusicaService(FavoritaGateway favoritaGateway,
                                                   BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                                                   BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase) {
        this.favoritaGateway = favoritaGateway;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.buscarMusicaPorIdUseCase = buscarMusicaPorIdUseCase;
    }

    @Transactional
    public void execute(Long usuarioId, Long musicaId) {
        buscarUsuarioPorIdUseCase.buscarUsuario(usuarioId);
        buscarMusicaPorIdUseCase.buscarMusica(musicaId);

        Favorita favorita = favoritaGateway.findByUsuarioIdAndMusicaId(usuarioId, musicaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Favorita not found for usuarioId: " + usuarioId + " and musicaId: " + musicaId));

        favoritaGateway.delete(favorita);
    }
}


