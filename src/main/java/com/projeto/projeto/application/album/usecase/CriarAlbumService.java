package com.projeto.projeto.application.album.usecase;

import com.projeto.projeto.application.album.port.in.*;


import com.projeto.projeto.application.album.mapper.AlbumMapper;
import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.application.artista.port.in.BuscarArtistaPorIdUseCase;
import com.projeto.projeto.application.album.dto.AlbumRequest;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.application.produtora.port.in.BuscarProdutoraPorIdUseCase;
import com.projeto.projeto.domain.album.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CriarAlbumService implements CriarAlbumUseCase {

    private final AlbumGateway albumGateway;
    private final AlbumMapper albumMapper;
    private final BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase;
    private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;

    public CriarAlbumService(AlbumGateway albumGateway,
                             AlbumMapper albumMapper,
                             BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase,
                             BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase) {
        this.albumGateway = albumGateway;
        this.albumMapper = albumMapper;
        this.buscarProdutoraPorIdUseCase = buscarProdutoraPorIdUseCase;
        this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
    }

    @Transactional
    public AlbumResponse execute(AlbumRequest albumRequest) {

        if (albumGateway.existsByNome(albumRequest.nome())) {
            throw new RuntimeException("Ja existe um album cadastrado com este nome.");
        }

        Album album = new Album();
        albumMapper.converterDTOParaEntity(album, albumRequest);
        album.setProdutora(buscarProdutoraPorIdUseCase.buscarProdutora(albumRequest.produtoraId()));
        album.setArtista(buscarArtistaPorIdUseCase.buscarArtista(albumRequest.artistaId()));

        return albumMapper.converterEntityParaDTO(albumGateway.save(album));
    }

}

