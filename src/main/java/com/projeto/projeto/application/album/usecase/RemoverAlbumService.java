package com.projeto.projeto.application.album.usecase;

import com.projeto.projeto.application.album.port.in.*;


import com.projeto.projeto.application.album.port.out.AlbumGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverAlbumService implements RemoverAlbumUseCase {

    private final AlbumGateway albumGateway;
    private final BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase;

    public RemoverAlbumService(AlbumGateway albumGateway,
                               BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase) {
        this.albumGateway = albumGateway;
        this.buscarAlbumPorIdUseCase = buscarAlbumPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        albumGateway.delete(buscarAlbumPorIdUseCase.buscarAlbum(id));
    }
}