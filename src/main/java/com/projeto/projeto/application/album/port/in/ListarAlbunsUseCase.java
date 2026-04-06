package com.projeto.projeto.application.album.port.in;

import com.projeto.projeto.application.album.mapper.AlbumMapper;
import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.domain.album.model.Album;
import java.util.List;

public interface ListarAlbunsUseCase {
    List<AlbumResponse> execute();
}
