package com.projeto.projeto.application.artista.usecase;

import com.projeto.projeto.application.artista.port.in.*;


import com.projeto.projeto.application.artista.mapper.ArtistaMapper;
import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ListarArtistasService implements ListarArtistasUseCase {

    private final ArtistaGateway artistaGateway;
    private final ArtistaMapper artistaMapper;

    public ListarArtistasService(ArtistaGateway artistaGateway, ArtistaMapper artistaMapper) {
        this.artistaGateway = artistaGateway;
        this.artistaMapper = artistaMapper;
    }

    @Transactional
    public List<ArtistaResponse> execute() {
        return artistaGateway.findAll().stream().map(artistaMapper::converterEntityParaDTO).toList();
    }
}
