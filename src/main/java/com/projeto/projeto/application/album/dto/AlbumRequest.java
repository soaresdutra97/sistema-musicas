package com.projeto.projeto.application.album.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AlbumRequest(

    @NotBlank(message = "O nome do album e obrigatorio.")
    @Size(max = 80, message = "O nome do album deve ter no maximo 80 caracteres.")
    String nome,

    @NotNull(message = "A data de lancamento e obrigatoria.")
    LocalDate dataLancamento,

    @NotNull(message = "O ID da produtora e obrigatorio.")
    Long produtoraId,

    @NotNull(message = "O ID do artista e obrigatorio.")
    Long artistaId

) {
}
