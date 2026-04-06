package com.projeto.projeto.application.album.port.out;

import com.projeto.projeto.domain.album.model.Album;
import java.util.List;
import java.util.Optional;

public interface AlbumGateway {

    Album save(Album album);

    Optional<Album> findById(Long id);

    List<Album> findAll();

    void delete(Album album);

    boolean existsById(Long id);
    
    boolean existsByNome(String nome);

}

