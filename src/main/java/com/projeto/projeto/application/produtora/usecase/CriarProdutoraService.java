package com.projeto.projeto.application.produtora.usecase;

import com.projeto.projeto.application.produtora.port.in.*;


import com.projeto.projeto.application.produtora.dto.ProdutoraRequest;
import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.mapper.ProdutoraMapper;
import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import com.projeto.projeto.domain.produtora.model.Produtora;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CriarProdutoraService implements CriarProdutoraUseCase {

    private final ProdutoraGateway produtoraGateway;
    private final ProdutoraMapper produtoraMapper;

    public CriarProdutoraService(ProdutoraGateway produtoraGateway, ProdutoraMapper produtoraMapper) {
        this.produtoraGateway = produtoraGateway;
        this.produtoraMapper = produtoraMapper;
    }

    @Transactional
    public ProdutoraResponse execute(ProdutoraRequest produtoraRequest) {
        if (produtoraGateway.existsByNome(produtoraRequest.nome())) {
            throw new RuntimeException("Ja existe uma produtora cadastrada com este nome.");
        }

        Produtora produtora = new Produtora();
        produtoraMapper.converterDTOParaEntity(produtora, produtoraRequest);

        return produtoraMapper.converterEntityParaDTO(produtoraGateway.save(produtora));
    }
}



