package com.projeto.projeto.application.estilo.usecase;

import com.projeto.projeto.application.estilo.port.in.*;


import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.mapper.EstiloMapper;
import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import com.projeto.projeto.domain.estilo.model.Estilo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarEstiloPorIdService implements BuscarEstiloPorIdUseCase {

    private final EstiloGateway estiloGateway;
    private final EstiloMapper estiloMapper;

    public BuscarEstiloPorIdService(EstiloGateway estiloGateway, EstiloMapper estiloMapper) {
        this.estiloGateway = estiloGateway;
        this.estiloMapper = estiloMapper;
    }

    @Transactional(readOnly = true)
    public EstiloResponse execute(Long id) {
        return estiloMapper.converterEntityParaDTO(buscarEstilo(id));
    }

    @Transactional(readOnly = true)
    public Estilo buscarEstilo(Long id) {
        return estiloGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Estilo not found with id: " + id));
    }
}



