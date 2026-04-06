package com.projeto.projeto.application.produtora.usecase;

import com.projeto.projeto.application.produtora.port.in.*;


import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.mapper.ProdutoraMapper;
import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import com.projeto.projeto.domain.produtora.model.Produtora;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarProdutoraPorIdService implements BuscarProdutoraPorIdUseCase {

    private final ProdutoraGateway produtoraGateway;
    private final ProdutoraMapper produtoraMapper;

    public BuscarProdutoraPorIdService(ProdutoraGateway produtoraGateway, ProdutoraMapper produtoraMapper) {
        this.produtoraGateway = produtoraGateway;
        this.produtoraMapper = produtoraMapper;
    }

    @Transactional(readOnly = true)
    public ProdutoraResponse execute(Long id) {
        return produtoraMapper.converterEntityParaDTO(buscarProdutora(id));
    }

    @Transactional(readOnly = true)
    public Produtora buscarProdutora(Long id) {
        return produtoraGateway.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produtora not found with id: " + id));
    }
}



