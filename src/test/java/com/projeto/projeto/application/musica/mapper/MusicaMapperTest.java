package com.projeto.projeto.application.musica.mapper;

import com.projeto.projeto.application.musica.dto.MusicaRequest;
import com.projeto.projeto.domain.musica.model.Musica;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MusicaMapperTest {

    private final MusicaMapper mapper = new MusicaMapper();

    @Test
    void deveInicializarContadoresComZeroQuandoNulos() {
        Musica musica = new Musica();

        MusicaRequest request = new MusicaRequest(
                "Bohemian Rhapsody",
                LocalDate.of(1975, 10, 31),
                1L,
                1L,
                1L,
                1L
        );

        mapper.converterDTOParaEntity(musica, request);

        assertEquals(0, musica.getVezesReproduzida());
        assertEquals(0, musica.getVezesFavoritada());
        assertEquals(0, musica.getNumeroCurtidas());
    }
}


