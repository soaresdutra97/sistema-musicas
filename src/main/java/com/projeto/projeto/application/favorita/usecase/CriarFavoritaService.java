package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.dto.FavoritaRequest;
import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.domain.favorita.model.Favorita;
import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.domain.usuario.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CriarFavoritaService implements CriarFavoritaUseCase {

    private final FavoritaGateway favoritaGateway;
    private final FavoritaMapper favoritaMapper;

    public CriarFavoritaService(FavoritaGateway favoritaGateway, FavoritaMapper favoritaMapper) {
        this.favoritaGateway = favoritaGateway;
        this.favoritaMapper = favoritaMapper;
    }

    @Transactional
    public FavoritaResponse execute(FavoritaRequest favoritaRequest) {
        if (favoritaGateway.existsByUsuarioIdAndMusicaId(favoritaRequest.usuarioId(), favoritaRequest.musicaId())) {
            throw new RuntimeException("Usuario ja favoritou esta musica.");
        }

        Favorita favorita = new Favorita();
        favoritaMapper.converterDTOParaEntity(favorita, favoritaRequest);
        favorita.setUsuario(criarUsuarioReferencia(favoritaRequest.usuarioId()));
        favorita.setMusica(criarMusicaReferencia(favoritaRequest.musicaId()));

        return favoritaMapper.converterEntityParaDTO(favoritaGateway.save(favorita));
    }

    private Usuario criarUsuarioReferencia(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }

    private Musica criarMusicaReferencia(Long musicaId) {
        Musica musica = new Musica();
        musica.setId(musicaId);
        return musica;
    }
}



