package com.projeto.projeto.application.musica.port.in;

import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.domain.musica.model.Musica;

public interface BuscarMusicaPorIdUseCase {
    MusicaResponse execute(Long id);
    Musica buscarMusica(Long id);
}
