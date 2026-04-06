package com.projeto.projeto.infrastructure.adapter.outbound.persistence.album.gateway;

import com.projeto.projeto.application.album.port.out.AlbumGateway;
import com.projeto.projeto.domain.album.model.Album;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.album.repository.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumJpaGateway implements AlbumGateway {

    private final AlbumRepository albumRepository;

    public AlbumJpaGateway(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public void delete(Album album) {
        albumRepository.delete(album);
    }

    @Override
    public boolean existsById(Long id) {
        return albumRepository.existsById(id);
    }

    @Override
    public boolean existsByNome(String nome) {
        return albumRepository.existsByNome(nome);
    }

}




