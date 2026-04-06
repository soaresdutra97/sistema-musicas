package com.projeto.projeto.application.favorita.mapper;

import com.projeto.projeto.application.favorita.dto.FavoritaRequest;
import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.domain.favorita.model.Favorita;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FavoritaMapper {

	public FavoritaResponse converterEntityParaDTO(Favorita favorita) {
		Long usuarioId = favorita.getUsuario() != null ? favorita.getUsuario().getId() : null;
		String usuarioNome = favorita.getUsuario() != null ? favorita.getUsuario().getNome() : null;
		Long musicaId = favorita.getMusica() != null ? favorita.getMusica().getId() : null;
		String musicaNome = favorita.getMusica() != null ? favorita.getMusica().getNome() : null;

		return new FavoritaResponse(
				favorita.getId(),
				usuarioId,
				usuarioNome,
				musicaId,
				musicaNome,
				favorita.getDataFavoritada()
		);
	}

	public void converterDTOParaEntity(Favorita favorita, FavoritaRequest favoritaRequest) {
		// Usuario e Musica devem ser resolvidos no use case por ID.
		favorita.setDataFavoritada(
				favoritaRequest.dataFavoritada() != null ? favoritaRequest.dataFavoritada() : LocalDate.now()
		);
	}
}


