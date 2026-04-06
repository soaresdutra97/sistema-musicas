package com.projeto.projeto.application.favorita.dto;

import java.time.LocalDate;

public record FavoritaResponse(
		Long id,
		Long usuarioId,
		String usuarioNome,
		Long musicaId,
		String musicaNome,
		LocalDate dataFavoritada
) {
}

