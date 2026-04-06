package com.projeto.projeto.application.artista.usecase;

import com.projeto.projeto.application.artista.port.in.*;


import com.projeto.projeto.application.artista.mapper.ArtistaMapper;
import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
import com.projeto.projeto.domain.artista.model.Artista;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarArtistaPorIdService implements BuscarArtistaPorIdUseCase {

    private final ArtistaGateway artistaGateway;
    private final ArtistaMapper artistaMapper;

    public BuscarArtistaPorIdService(ArtistaGateway artistaGateway, ArtistaMapper artistaMapper) {
        this.artistaGateway = artistaGateway;
        this.artistaMapper = artistaMapper;
    }

    @Transactional(readOnly = true)
    public ArtistaResponse execute(Long id) {
        return artistaMapper.converterEntityParaDTO(buscarArtista(id));
    }

    @Transactional(readOnly = true)
    public Artista buscarArtista(Long id) {
        return artistaGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Artista not found with id: " + id));
    }

}

