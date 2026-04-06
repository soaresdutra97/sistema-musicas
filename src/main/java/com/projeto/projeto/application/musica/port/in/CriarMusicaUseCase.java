package com.projeto.projeto.application.musica.port.in;

import com.projeto.projeto.application.album.port.in.BuscarAlbumPorIdUseCase;
import com.projeto.projeto.application.artista.port.in.BuscarArtistaPorIdUseCase;
import com.projeto.projeto.application.musica.dto.MusicaRequest;
import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.estilo.port.in.BuscarEstiloPorIdUseCase;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.domain.musica.model.Musica;

public interface CriarMusicaUseCase {
    MusicaResponse execute(MusicaRequest musicaRequest);
}
