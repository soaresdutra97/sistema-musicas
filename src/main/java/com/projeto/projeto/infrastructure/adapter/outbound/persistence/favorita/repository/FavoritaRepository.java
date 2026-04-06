package com.projeto.projeto.infrastructure.adapter.outbound.persistence.favorita.repository;

import com.projeto.projeto.domain.favorita.model.Favorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritaRepository extends JpaRepository<Favorita, Long> {

	List<Favorita> findByUsuario_Id(Long usuarioId);

	Optional<Favorita> findByUsuario_IdAndMusica_Id(Long usuarioId, Long musicaId);

	long deleteAllByUsuario_Id(Long usuarioId);

	boolean existsByUsuario_IdAndMusica_Id(Long usuarioId, Long musicaId);
}


