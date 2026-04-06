package com.projeto.projeto.application.album.usecase;

import com.projeto.projeto.application.album.port.in.*;



import com.projeto.projeto.domain.album.model.Album;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class BuscarAlbumPorIdsService implements BuscarAlbumPorIdsUseCase {

    private final BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase;

    public BuscarAlbumPorIdsService(BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase) {
        this.buscarAlbumPorIdUseCase = buscarAlbumPorIdUseCase;
    }


    @Transactional(readOnly = true)
    public Set<Album> execute(Set<Long> ids) {

        Set<Album> albuns = new LinkedHashSet<>();

        for (Long id : ids) {
            albuns.add(buscarAlbumPorIdUseCase.buscarAlbum(id));
        }
        return albuns;
    }

    @Deprecated(forRemoval = false)
    public Set<Album> executar(Set<Long> ids) {
        return execute(ids);
    }
}
