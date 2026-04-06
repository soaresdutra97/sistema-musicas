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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class AtualizarMusicaService implements AtualizarMusicaUseCase {

    private final MusicaGateway musicaGateway;
    private final BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase;
    private final MusicaMapper musicaMapper;
    private final BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase;
    private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;
    private final BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase;

    public AtualizarMusicaService(MusicaGateway musicaGateway,
                                  BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase,
                                  MusicaMapper musicaMapper,
                                  BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase,
                                  BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase,
                                  BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase) {
        this.musicaGateway = musicaGateway;
        this.buscarMusicaPorIdUseCase = buscarMusicaPorIdUseCase;
        this.musicaMapper = musicaMapper;
        this.buscarAlbumPorIdUseCase = buscarAlbumPorIdUseCase;
        this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
        this.buscarEstiloPorIdUseCase = buscarEstiloPorIdUseCase;
    }

    @Transactional
    public MusicaResponse execute(Long id, MusicaRequest musicaRequest) {

        Musica musica = buscarMusicaPorIdUseCase.buscarMusica(id);

        if(!musica.getNome().equals(musicaRequest.nome()) && musicaGateway.existsByNome(musicaRequest.nome())) {
            throw new RuntimeException("Ja existe uma musica cadastrada com este nome.");
        }

        musicaMapper.converterDTOParaEntity(musica, musicaRequest);
        musica.setAlbum(buscarAlbumPorIdUseCase.buscarAlbum(musicaRequest.albumId()));
        musica.setArtista(buscarArtistaPorIdUseCase.buscarArtista(musicaRequest.artistaId()));
        musica.setEstilo(buscarEstiloPorIdUseCase.buscarEstilo(musicaRequest.estiloId()));

        return musicaMapper.converterEntityParaDTO(musicaGateway.save(musica));

    }

}


