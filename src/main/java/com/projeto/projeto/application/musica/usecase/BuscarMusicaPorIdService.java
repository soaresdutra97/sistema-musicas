package com.projeto.projeto.application.musica.usecase;

import com.projeto.projeto.application.musica.port.in.*;


import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.domain.musica.model.Musica;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarMusicaPorIdService implements BuscarMusicaPorIdUseCase {

    private final MusicaGateway musicaGateway;
    private final MusicaMapper musicaMapper;

    public BuscarMusicaPorIdService(MusicaGateway musicaGateway, MusicaMapper musicaMapper) {
        this.musicaGateway = musicaGateway;
        this.musicaMapper = musicaMapper;
    }

    @Transactional(readOnly = true)
    public MusicaResponse execute(Long id) {
        return musicaMapper.converterEntityParaDTO(buscarMusica(id));
    }

    @Transactional
    public Musica buscarMusica(Long id) {
        return musicaGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Musica not found with id: " + id));
    }

}


