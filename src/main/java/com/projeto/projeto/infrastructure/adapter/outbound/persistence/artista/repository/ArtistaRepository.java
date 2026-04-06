package com.projeto.projeto.infrastructure.adapter.outbound.persistence.artista.repository;

import com.projeto.projeto.domain.artista.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {


    boolean existsByNome(String nome);

}



