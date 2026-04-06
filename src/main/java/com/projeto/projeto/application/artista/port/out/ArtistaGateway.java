package com.projeto.projeto.application.artista.port.out;

import com.projeto.projeto.domain.artista.model.Artista;

import java.util.List;
import java.util.Optional;

public interface ArtistaGateway {

    Artista save(Artista artista);

    Optional<Artista> findById(Long id);

    List<Artista> findAll();

    void delete(Artista artista);

    boolean existsById(Long id);

    boolean existsByNome(String nome);

}

