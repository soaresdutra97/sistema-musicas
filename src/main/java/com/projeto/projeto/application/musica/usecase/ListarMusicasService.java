package com.projeto.projeto.application.musica.usecase;

import com.projeto.projeto.application.musica.port.in.*;


import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ListarMusicasService implements ListarMusicasUseCase {

    private final MusicaGateway musicaGateway;
    private final MusicaMapper musicaMapper;

    public ListarMusicasService(MusicaGateway musicaGateway, MusicaMapper musicaMapper) {
        this.musicaGateway = musicaGateway;
        this.musicaMapper = musicaMapper;
    }


    @Transactional(readOnly = true)
    public List<MusicaResponse> execute() {
        return musicaGateway.findAll().stream().map(musicaMapper::converterEntityParaDTO).toList();
    }

    @Deprecated(forRemoval = false)
    public List<MusicaResponse> executar() {
        return execute();
    }

}

