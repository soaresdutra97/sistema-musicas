package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.in.*;


import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.domain.favorita.model.Favorita;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarFavoritaPorIdService implements BuscarFavoritaPorIdUseCase {

    private final FavoritaGateway favoritaGateway;
    private final FavoritaMapper favoritaMapper;

    public BuscarFavoritaPorIdService(FavoritaGateway favoritaGateway, FavoritaMapper favoritaMapper) {
        this.favoritaGateway = favoritaGateway;
        this.favoritaMapper = favoritaMapper;
    }

    @Transactional(readOnly = true)
    public FavoritaResponse execute(Long id) {
        return favoritaMapper.converterEntityParaDTO(buscarFavorita(id));
    }

    @Transactional(readOnly = true)
    public Favorita buscarFavorita(Long id) {
        return favoritaGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Favorita not found with id: " + id));
    }
}



