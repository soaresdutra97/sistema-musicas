package com.projeto.projeto.application.produtora.usecase;

import com.projeto.projeto.application.produtora.port.in.*;


import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RemoverProdutoraService implements RemoverProdutoraUseCase {

    private final ProdutoraGateway produtoraGateway;
    private final BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase;

    public RemoverProdutoraService(ProdutoraGateway produtoraGateway,
                                   BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase) {
        this.produtoraGateway = produtoraGateway;
        this.buscarProdutoraPorIdUseCase = buscarProdutoraPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        produtoraGateway.delete(buscarProdutoraPorIdUseCase.buscarProdutora(id));
    }
}

