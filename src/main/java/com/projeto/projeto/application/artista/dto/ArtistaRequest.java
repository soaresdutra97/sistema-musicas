package com.projeto.projeto.application.artista.dto;

import jakarta.validation.constraints.NotNull;

public record ArtistaRequest(

        @NotNull(message = "O nome do artista e obrigatorio.")
        String nome
) {
}

