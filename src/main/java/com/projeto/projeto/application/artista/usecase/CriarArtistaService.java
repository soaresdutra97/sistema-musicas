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
public class CriarArtistaService implements CriarArtistaUseCase {

    private final ArtistaGateway artistaGateway;
    private final ArtistaMapper artistaMapper;

    public CriarArtistaService(ArtistaGateway artistaGateway, ArtistaMapper artistaMapper) {
        this.artistaGateway = artistaGateway;
        this.artistaMapper = artistaMapper;
    }

    @Transactional
    public ArtistaResponse execute(ArtistaRequest artistaRequest) {

        if (artistaGateway.existsByNome(artistaRequest.nome())) {
            throw new RuntimeException("Ja existe um artista cadastrado com este nome.");
        }

        Artista artista = new Artista();
        artistaMapper.converterDTOParaEntity(artista, artistaRequest);

        return artistaMapper.converterEntityParaDTO(artistaGateway.save(artista));
    }
}



