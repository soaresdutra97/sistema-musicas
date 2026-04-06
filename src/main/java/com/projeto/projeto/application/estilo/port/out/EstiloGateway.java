package com.projeto.projeto.application.estilo.port.out;

import com.projeto.projeto.domain.estilo.model.Estilo;

import java.util.List;
import java.util.Optional;

public interface EstiloGateway {

    Estilo save(Estilo estilo);

    Optional<Estilo> findById(Long id);

    List<Estilo> findAll();

    void delete(Estilo estilo);

    boolean existsById(Long id);

    boolean existsByNome(String nome);
}

