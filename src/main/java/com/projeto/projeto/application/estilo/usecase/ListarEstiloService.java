package com.projeto.projeto.application.estilo.usecase;

import com.projeto.projeto.application.estilo.port.in.*;


import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.mapper.EstiloMapper;
import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarEstiloService implements ListarEstiloUseCase {

    private final EstiloGateway estiloGateway;
    private final EstiloMapper estiloMapper;

    public ListarEstiloService(EstiloGateway estiloGateway, EstiloMapper estiloMapper) {
        this.estiloGateway = estiloGateway;
        this.estiloMapper = estiloMapper;
    }

    @Transactional
    public List<EstiloResponse> execute() {
        return estiloGateway.findAll().stream().map(estiloMapper::converterEntityParaDTO).toList();
    }
}


