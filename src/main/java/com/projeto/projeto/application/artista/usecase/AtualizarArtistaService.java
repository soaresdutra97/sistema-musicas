package com.projeto.projeto.application.artista.usecase;

import com.projeto.projeto.application.artista.port.in.*;


import com.projeto.projeto.application.artista.mapper.ArtistaMapper;
import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.application.artista.dto.ArtistaRequest;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
import com.projeto.projeto.domain.artista.model.Artista;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AtualizarArtistaService implements AtualizarArtistaUseCase {

    private final ArtistaGateway artistaGateway;
    private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;
    private final ArtistaMapper artistaMapper;

    public AtualizarArtistaService(ArtistaGateway artistaGateway, BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase, ArtistaMapper artistaMapper) {
        this.artistaGateway = artistaGateway;
        this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
        this.artistaMapper = artistaMapper;
    }

    @Transactional

    public ArtistaResponse execute(Long id, ArtistaRequest artistaRequest) {

        Artista artista = buscarArtistaPorIdUseCase.buscarArtista(id);

        if(!artista.getNome().equals(artistaRequest.nome()) && artistaGateway.existsByNome(artistaRequest.nome())) {
            throw new RuntimeException("Ja existe um artista cadastrado com este nome.");
        }

        artistaMapper.converterDTOParaEntity(artista, artistaRequest);

        return artistaMapper.converterEntityParaDTO(artistaGateway.save(artista));

    }

}


