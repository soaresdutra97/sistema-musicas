package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarFavoritasService implements ListarFavoritasUseCase {

    private final FavoritaGateway favoritaGateway;
    private final FavoritaMapper favoritaMapper;

    public ListarFavoritasService(FavoritaGateway favoritaGateway, FavoritaMapper favoritaMapper) {
        this.favoritaGateway = favoritaGateway;
        this.favoritaMapper = favoritaMapper;
    }

    @Transactional
    public List<FavoritaResponse> execute() {
        return favoritaGateway.findAll().stream().map(favoritaMapper::converterEntityParaDTO).toList();
    }
}


