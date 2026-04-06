package com.projeto.projeto.application.estilo.usecase;

import com.projeto.projeto.application.estilo.port.in.*;


import com.projeto.projeto.application.estilo.dto.EstiloRequest;
import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.mapper.EstiloMapper;
import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import com.projeto.projeto.domain.estilo.model.Estilo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CriarEstiloService implements CriarEstiloUseCase {

    private final EstiloGateway estiloGateway;
    private final EstiloMapper estiloMapper;

    public CriarEstiloService(EstiloGateway estiloGateway, EstiloMapper estiloMapper) {
        this.estiloGateway = estiloGateway;
        this.estiloMapper = estiloMapper;
    }

    @Transactional
    public EstiloResponse execute(EstiloRequest estiloRequest) {
        if (estiloGateway.existsByNome(estiloRequest.nome())) {
            throw new RuntimeException("Ja existe um estilo cadastrado com este nome.");
        }

        Estilo estilo = new Estilo();
        estiloMapper.converterDTOParaEntity(estilo, estiloRequest);

        return estiloMapper.converterEntityParaDTO(estiloGateway.save(estilo));
    }
}



