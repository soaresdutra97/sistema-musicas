package com.projeto.projeto.application.musica.dto;

import java.time.LocalDate;

public record MusicaResponse(

        Long id,
        String nome,
        LocalDate dataLancamento,
        String albumNome,
        String artistaNome,
        String estiloNome,
        Integer vezesReproduzida,
        Integer vezesFavoritada,
        Integer numeroCurtidas

        )
{
}
