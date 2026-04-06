package com.projeto.projeto.application.produtora.port.out;

import com.projeto.projeto.domain.produtora.model.Produtora;

import java.util.List;
import java.util.Optional;

public interface ProdutoraGateway {

    Produtora save(Produtora produtora);

    Optional<Produtora> findById(Long id);

    List<Produtora> findAll();

    void delete(Produtora produtora);

    boolean existsById(Long id);

    boolean existsByNome(String nome);
}


