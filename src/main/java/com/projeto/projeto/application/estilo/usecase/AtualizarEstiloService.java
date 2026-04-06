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
public class AtualizarEstiloService implements AtualizarEstiloUseCase {

    private final EstiloGateway estiloGateway;
    private final BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase;
    private final EstiloMapper estiloMapper;

    public AtualizarEstiloService(EstiloGateway estiloGateway,
                                  BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase,
                                  EstiloMapper estiloMapper) {
        this.estiloGateway = estiloGateway;
        this.buscarEstiloPorIdUseCase = buscarEstiloPorIdUseCase;
        this.estiloMapper = estiloMapper;
    }

    @Transactional
    public EstiloResponse execute(Long id, EstiloRequest estiloRequest) {
        Estilo estilo = buscarEstiloPorIdUseCase.buscarEstilo(id);

        if (!estilo.getNome().equals(estiloRequest.nome()) && estiloGateway.existsByNome(estiloRequest.nome())) {
            throw new RuntimeException("Ja existe um estilo cadastrado com este nome.");
        }

        estiloMapper.converterDTOParaEntity(estilo, estiloRequest);

        return estiloMapper.converterEntityParaDTO(estiloGateway.save(estilo));
    }
}

