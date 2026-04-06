package com.projeto.projeto.application.produtora.port.in;

import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;

public interface RemoverProdutoraUseCase {
    void execute(Long id);
}
