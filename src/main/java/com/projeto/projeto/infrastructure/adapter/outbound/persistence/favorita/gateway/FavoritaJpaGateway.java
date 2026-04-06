package com.projeto.projeto.infrastructure.adapter.outbound.persistence.favorita.gateway;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.domain.favorita.model.Favorita;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.favorita.repository.FavoritaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FavoritaJpaGateway implements FavoritaGateway {

	private final FavoritaRepository favoritaRepository;

	public FavoritaJpaGateway(FavoritaRepository favoritaRepository) {
		this.favoritaRepository = favoritaRepository;
	}

	@Override
	public Favorita save(Favorita favorita) {
		return favoritaRepository.save(favorita);
	}

	@Override
	public Optional<Favorita> findById(Long id) {
		return favoritaRepository.findById(id);
	}

	@Override
	public Optional<Favorita> findByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId) {
		return favoritaRepository.findByUsuario_IdAndMusica_Id(usuarioId, musicaId);
	}

	@Override
	public List<Favorita> findAll() {
		return favoritaRepository.findAll();
	}

	@Override
	public List<Favorita> findByUsuarioId(Long usuarioId) {
		return favoritaRepository.findByUsuario_Id(usuarioId);
	}

	@Override
	public void delete(Favorita favorita) {
		favoritaRepository.delete(favorita);
	}

	@Override
	public void deleteByUsuarioId(Long usuarioId) {
		favoritaRepository.deleteAllByUsuario_Id(usuarioId);
	}

	@Override
	public boolean existsById(Long id) {
		return favoritaRepository.existsById(id);
	}

	@Override
	public boolean existsByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId) {
		return favoritaRepository.existsByUsuario_IdAndMusica_Id(usuarioId, musicaId);
	}
}



