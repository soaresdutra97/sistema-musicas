package com.projeto.projeto.application.musica.usecase;

import com.projeto.projeto.application.musica.port.in.*;


import com.projeto.projeto.application.album.port.in.BuscarAlbumPorIdUseCase;
import com.projeto.projeto.application.artista.port.in.BuscarArtistaPorIdUseCase;
import com.projeto.projeto.application.musica.dto.MusicaRequest;
import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.estilo.port.in.BuscarEstiloPorIdUseCase;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.domain.musica.model.Musica;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarMusicaService implements CriarMusicaUseCase {

    private final MusicaGateway musicaGateway;
    private final MusicaMapper musicaMapper;
    private final BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase;
    private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;
    private final BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase;

    public CriarMusicaService(MusicaGateway musicaGateway,
                              MusicaMapper musicaMapper,
                              BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase,
                              BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase,
                              BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase) {
        this.musicaGateway = musicaGateway;
        this.musicaMapper = musicaMapper;
        this.buscarAlbumPorIdUseCase = buscarAlbumPorIdUseCase;
        this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
        this.buscarEstiloPorIdUseCase = buscarEstiloPorIdUseCase;
    }

    @Transactional
    public MusicaResponse execute(MusicaRequest musicaRequest) {

        if(musicaGateway.existsByNome(musicaRequest.nome())){
            throw new RuntimeException("Ja existe uma musica cadastrada com este nome.");
        }

        Musica musica = new Musica();
        musicaMapper.converterDTOParaEntity(musica, musicaRequest);
        musica.setAlbum(buscarAlbumPorIdUseCase.buscarAlbum(musicaRequest.albumId()));
        musica.setArtista(buscarArtistaPorIdUseCase.buscarArtista(musicaRequest.artistaId()));
        musica.setEstilo(buscarEstiloPorIdUseCase.buscarEstilo(musicaRequest.estiloId()));

        return musicaMapper.converterEntityParaDTO(musicaGateway.save(musica));

    }

}


