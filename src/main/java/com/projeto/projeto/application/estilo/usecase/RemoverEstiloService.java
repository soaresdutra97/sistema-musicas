package com.projeto.projeto.application.estilo.usecase;

import com.projeto.projeto.application.estilo.port.in.*;


import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverEstiloService implements RemoverEstiloUseCase {

    private final EstiloGateway estiloGateway;
    private final BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase;

    public RemoverEstiloService(EstiloGateway estiloGateway,
                                BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase) {
        this.estiloGateway = estiloGateway;
        this.buscarEstiloPorIdUseCase = buscarEstiloPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        estiloGateway.delete(buscarEstiloPorIdUseCase.buscarEstilo(id));
    }
}

