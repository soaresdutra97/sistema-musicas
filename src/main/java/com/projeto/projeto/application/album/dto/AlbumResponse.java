package com.projeto.projeto.application.album.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record AlbumResponse(

    Long id,
    String nome,
    LocalDate dataLancamento,
    String produtoraNome,
    String artistaNome,
    List<String> musicasNomes

) {
}

