package com.projeto.projeto.application.produtora.port.in;

import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.mapper.ProdutoraMapper;
import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import java.util.List;

public interface ListarProdutorasUseCase {
    List<ProdutoraResponse> execute();
}
