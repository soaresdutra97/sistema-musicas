package com.projeto.projeto.application.estilo.port.in;

import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.mapper.EstiloMapper;
import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import java.util.List;

public interface ListarEstiloUseCase {
    List<EstiloResponse> execute();
}
