package com.projeto.projeto.application.produtora.usecase;

import com.projeto.projeto.application.produtora.port.in.*;


import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.mapper.ProdutoraMapper;
import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarProdutorasService implements ListarProdutorasUseCase {

    private final ProdutoraGateway produtoraGateway;
    private final ProdutoraMapper produtoraMapper;

    public ListarProdutorasService(ProdutoraGateway produtoraGateway, ProdutoraMapper produtoraMapper) {
        this.produtoraGateway = produtoraGateway;
        this.produtoraMapper = produtoraMapper;
    }

    @Transactional
    public List<ProdutoraResponse> execute() {
        return produtoraGateway.findAll().stream().map(produtoraMapper::converterEntityParaDTO).toList();
    }
}


