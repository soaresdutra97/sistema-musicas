package com.projeto.projeto.application.estilo.mapper;

import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.dto.EstiloRequest;
import com.projeto.projeto.domain.estilo.model.Estilo;
import org.springframework.stereotype.Component;

@Component
public class EstiloMapper {

    public EstiloResponse converterEntityParaDTO(Estilo estilo) {
        return new EstiloResponse(
            estilo.getId(),
            estilo.getNome()
        );
    }

    public void converterDTOParaEntity(Estilo estilo, EstiloRequest estiloRequest) {
        estilo.setNome(estiloRequest.nome());
    }
}

