package com.projeto.projeto.application.artista.usecase;

import com.projeto.projeto.application.artista.port.in.*;


import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RemoverArtistaService implements RemoverArtistaUseCase {

    private final ArtistaGateway artistaGateway;
    private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;

    public RemoverArtistaService(ArtistaGateway artistaGateway, BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase) {
        this.artistaGateway = artistaGateway;
        this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        artistaGateway.delete(buscarArtistaPorIdUseCase.buscarArtista(id));
    }
}