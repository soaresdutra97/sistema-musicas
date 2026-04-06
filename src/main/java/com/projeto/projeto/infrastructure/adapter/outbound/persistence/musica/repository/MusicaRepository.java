package com.projeto.projeto.infrastructure.adapter.outbound.persistence.musica.repository;

import com.projeto.projeto.domain.musica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    boolean existsByNome(String nome);

}




