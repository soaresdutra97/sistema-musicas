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
public class AtualizarFavoritaService implements AtualizarFavoritaUseCase {

    private final FavoritaGateway favoritaGateway;
    private final BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase;
    private final FavoritaMapper favoritaMapper;

    public AtualizarFavoritaService(FavoritaGateway favoritaGateway,
                                    BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase,
                                    FavoritaMapper favoritaMapper) {
        this.favoritaGateway = favoritaGateway;
        this.buscarFavoritaPorIdUseCase = buscarFavoritaPorIdUseCase;
        this.favoritaMapper = favoritaMapper;
    }

    @Transactional
    public FavoritaResponse execute(Long id, FavoritaRequest favoritaRequest) {
        Favorita favorita = buscarFavoritaPorIdUseCase.buscarFavorita(id);

        boolean alterouUsuarioOuMusica = !favorita.getUsuario().getId().equals(favoritaRequest.usuarioId())
                || !favorita.getMusica().getId().equals(favoritaRequest.musicaId());

        if (alterouUsuarioOuMusica
                && favoritaGateway.existsByUsuarioIdAndMusicaId(favoritaRequest.usuarioId(), favoritaRequest.musicaId())) {
            throw new RuntimeException("Usuario ja favoritou esta musica.");
        }

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



