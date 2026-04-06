package com.projeto.projeto.application.album.usecase;

import com.projeto.projeto.application.album.port.in.*;


import com.projeto.projeto.application.album.mapper.AlbumMapper;
import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.domain.album.model.Album;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarAlbumPorIdService implements BuscarAlbumPorIdUseCase {

    private final AlbumGateway albumGateway;
    private final AlbumMapper albumMapper;


    public BuscarAlbumPorIdService(AlbumGateway albumGateway, AlbumMapper albumMapper) {
        this.albumGateway = albumGateway;
        this.albumMapper = albumMapper;
    }

    @Transactional(readOnly = true)
    public AlbumResponse execute(Long id){
        return albumMapper.converterEntityParaDTO(buscarAlbum(id));
    }

    @Deprecated(forRemoval = false)
    public AlbumResponse executar(Long id) {
        return execute(id);
    }

    @Transactional(readOnly = true)
    public Album buscarAlbum(Long id){
        return albumGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Album not found with id: " + id));
    }
}

