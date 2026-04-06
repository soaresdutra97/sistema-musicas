package com.projeto.projeto.infrastructure.adapter.outbound.persistence.estilo.repository;

import com.projeto.projeto.domain.estilo.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstiloRepository extends JpaRepository<Estilo, Long> {

	boolean existsByNome(String nome);
}



