package com.projeto.projeto.application.album.mapper;

import com.projeto.projeto.application.album.dto.AlbumRequest;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.domain.album.model.Album;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumMapper {

    public AlbumResponse converterEntityParaDTO(Album album) {
        return new AlbumResponse(
            album.getId(),
            album.getNome(),
            album.getDataLancamento(),
            album.getProdutora() != null ? album.getProdutora().getNome() : null,
            album.getArtista() != null ? album.getArtista().getNome() : null,
            album.getMusicas() != null ? album.getMusicas().stream().map(musica -> musica.getNome()).toList() : List.of()
        );
    }

    public void converterDTOParaEntity(Album album, AlbumRequest albumRequest) {
        album.setNome(albumRequest.nome());
        album.setDataLancamento(albumRequest.dataLancamento());
    }
}

