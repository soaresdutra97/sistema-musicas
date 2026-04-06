package com.projeto.projeto.application.album.port.in;

import com.projeto.projeto.application.album.port.out.AlbumGateway;

public interface RemoverAlbumUseCase {
    void execute(Long id);
}
