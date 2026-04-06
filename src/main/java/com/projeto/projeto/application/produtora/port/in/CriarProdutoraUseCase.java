package com.projeto.projeto.application.produtora.port.in;

import com.projeto.projeto.application.produtora.dto.ProdutoraRequest;
import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.mapper.ProdutoraMapper;
import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import com.projeto.projeto.domain.produtora.model.Produtora;

public interface CriarProdutoraUseCase {
    ProdutoraResponse execute(ProdutoraRequest produtoraRequest);
}
