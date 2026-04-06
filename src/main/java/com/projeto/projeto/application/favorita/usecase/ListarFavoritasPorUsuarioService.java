package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarFavoritasPorUsuarioService implements ListarFavoritasPorUsuarioUseCase {

    private final FavoritaGateway favoritaGateway;
    private final FavoritaMapper favoritaMapper;

    public ListarFavoritasPorUsuarioService(FavoritaGateway favoritaGateway, FavoritaMapper favoritaMapper) {
        this.favoritaGateway = favoritaGateway;
        this.favoritaMapper = favoritaMapper;
    }

    @Transactional
    public List<FavoritaResponse> execute(Long usuarioId) {
        return favoritaGateway.findByUsuarioId(usuarioId).stream().map(favoritaMapper::converterEntityParaDTO).toList();
    }
}


