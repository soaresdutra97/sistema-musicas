package com.projeto.projeto.application.musica.port.out;

import com.projeto.projeto.domain.artista.model.Artista;
import com.projeto.projeto.domain.musica.model.Musica;

import java.util.List;
import java.util.Optional;

public interface MusicaGateway {

    Musica save(Musica musica);

    Optional<Musica> findById(Long id);

    List<Musica> findAll();

    void delete(Musica musica);

    boolean existsById(Long id);

    boolean existsByNome(String nome);

}
