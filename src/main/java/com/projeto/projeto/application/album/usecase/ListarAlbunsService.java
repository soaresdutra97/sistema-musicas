package com.projeto.projeto.application.album.usecase;

import com.projeto.projeto.application.album.port.in.*;


import com.projeto.projeto.application.album.mapper.AlbumMapper;
import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.domain.album.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarAlbunsService implements ListarAlbunsUseCase {

    private final AlbumGateway albumGateway;
    private final AlbumMapper albumMapper;

    public ListarAlbunsService(AlbumGateway albumGateway, AlbumMapper albumMapper) {
        this.albumGateway = albumGateway;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public List<AlbumResponse> execute() {
        return albumGateway.findAll().stream().map(albumMapper::converterEntityParaDTO).toList();
    }
}

