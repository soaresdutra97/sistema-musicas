package com.projeto.projeto.application.estilo.port.in;

import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.mapper.EstiloMapper;
import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import com.projeto.projeto.domain.estilo.model.Estilo;

public interface BuscarEstiloPorIdUseCase {
    EstiloResponse execute(Long id);
    Estilo buscarEstilo(Long id);
}
