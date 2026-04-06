package com.projeto.projeto.infrastructure.adapter.outbound.persistence.album.repository;

import com.projeto.projeto.domain.album.model.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    boolean existsByNome(String nome);

    @Override
    @EntityGraph(attributePaths = {"musicas"})
    Optional<Album> findById(Long id);

}



