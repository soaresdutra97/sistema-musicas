package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.domain.favorita.model.Favorita;

public interface BuscarFavoritaPorIdUseCase {
    FavoritaResponse execute(Long id);
    Favorita buscarFavorita(Long id);
}
