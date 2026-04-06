package com.projeto.projeto.application.produtora.mapper;

import com.projeto.projeto.application.produtora.dto.ProdutoraRequest;
import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.domain.produtora.model.Produtora;
import org.springframework.stereotype.Component;

@Component
public class ProdutoraMapper {

    public ProdutoraResponse converterEntityParaDTO(Produtora produtora) {
        return new ProdutoraResponse(
                produtora.getId(),
                produtora.getNome()
        );
    }

    public void converterDTOParaEntity(Produtora produtora, ProdutoraRequest produtoraRequest) {
        produtora.setNome(produtoraRequest.nome());
    }
}



