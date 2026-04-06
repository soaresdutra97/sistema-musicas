package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.dto.FavoritaRequest;
import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.domain.favorita.model.Favorita;
import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.domain.usuario.model.Usuario;

public interface CriarFavoritaUseCase {
    FavoritaResponse execute(FavoritaRequest favoritaRequest);
}
