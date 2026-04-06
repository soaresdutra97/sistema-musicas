package com.projeto.projeto.infrastructure.adapter.outbound.persistence.produtora.repository;

import com.projeto.projeto.domain.produtora.model.Produtora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoraRepository extends JpaRepository<Produtora, Long> {

    boolean existsByNome(String nome);
}




