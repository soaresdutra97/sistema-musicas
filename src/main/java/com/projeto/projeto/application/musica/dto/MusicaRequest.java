package com.projeto.projeto.application.musica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MusicaRequest(

        @NotBlank(message = "O nome da musica e obrigatorio.")
        @Size(max = 80, message = "O nome da musica deve ter no maximo 80 caracteres.")
        String nome,

        @NotNull(message = "A data de lancamento da musica e obrigatoria.")
        LocalDate dataLancamento,

        @NotNull(message = "O ID do album e obrigatorio.")
        Long albumId,

        @NotNull(message = "O ID do artista e obrigatorio.")
        Long artistaId,

        @NotNull(message = "O ID da produtora e obrigatorio.")
        Long produtoraId,

        @NotNull(message = "O ID do estilo e obrigatorio.")
        Long estiloId

) {
}
