package com.projeto.projeto.application.artista.dto;

import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.application.musica.dto.MusicaResponse;

import java.util.List;

public record ArtistaResponse(

        Long id,
        String nome,
        List<MusicaResponse> musicasLancadas,
        List<AlbumResponse> albunsLancados

) {

}
