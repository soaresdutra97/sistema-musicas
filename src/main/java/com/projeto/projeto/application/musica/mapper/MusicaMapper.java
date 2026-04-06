package com.projeto.projeto.application.musica.mapper;

import com.projeto.projeto.application.musica.dto.MusicaRequest;
import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.domain.musica.model.Musica;
import org.springframework.stereotype.Component;

@Component
public class MusicaMapper {

    public MusicaResponse converterEntityParaDTO(Musica musica) {
        return new MusicaResponse(
            musica.getId(),
            musica.getNome(),
            musica.getDataLancamento(),
            musica.getAlbum() != null ? musica.getAlbum().getNome() : null,
            musica.getArtista() != null ? musica.getArtista().getNome() : null,
            musica.getEstilo() != null ? musica.getEstilo().getNome() : null,
            musica.getVezesReproduzida(),
            musica.getVezesFavoritada(),
            musica.getNumeroCurtidas()
        );
    }

    public void converterDTOParaEntity(Musica musica, MusicaRequest musicaRequest) {
        musica.setNome(musicaRequest.nome());
        musica.setDataLancamento(musicaRequest.dataLancamento());

        if (musica.getVezesReproduzida() == null) {
            musica.setVezesReproduzida(0);
        }
        if (musica.getVezesFavoritada() == null) {
            musica.setVezesFavoritada(0);
        }
        if (musica.getNumeroCurtidas() == null) {
            musica.setNumeroCurtidas(0);
        }
    }
}

