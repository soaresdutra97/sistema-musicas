package com.projeto.projeto.application.favorita.port.out;

import com.projeto.projeto.domain.favorita.model.Favorita;

import java.util.List;
import java.util.Optional;

public interface FavoritaGateway {

	Favorita save(Favorita favorita);

	Optional<Favorita> findById(Long id);

	Optional<Favorita> findByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId);

	List<Favorita> findAll();

	List<Favorita> findByUsuarioId(Long usuarioId);

	void delete(Favorita favorita);

	void deleteByUsuarioId(Long usuarioId);

	boolean existsById(Long id);

	boolean existsByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId);
}

