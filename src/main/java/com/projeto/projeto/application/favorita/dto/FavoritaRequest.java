package com.projeto.projeto.application.favorita.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FavoritaRequest(
		@NotNull(message = "Usuario id e obrigatorio")
		Long usuarioId,

		@NotNull(message = "Musica id e obrigatorio")
		Long musicaId,

		LocalDate dataFavoritada
) {
}

