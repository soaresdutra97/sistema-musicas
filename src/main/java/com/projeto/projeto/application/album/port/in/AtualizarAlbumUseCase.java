package com.projeto.projeto.application.album.port.in;

import com.projeto.projeto.application.album.mapper.AlbumMapper;
import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.application.artista.port.in.BuscarArtistaPorIdUseCase;
import com.projeto.projeto.application.album.dto.AlbumRequest;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.application.produtora.port.in.BuscarProdutoraPorIdUseCase;
import com.projeto.projeto.domain.album.model.Album;

public interface AtualizarAlbumUseCase {
    AlbumResponse execute(Long id, AlbumRequest albumRequest);
}
