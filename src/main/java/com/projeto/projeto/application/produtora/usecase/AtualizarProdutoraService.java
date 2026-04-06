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
public class AtualizarProdutoraService implements AtualizarProdutoraUseCase {

    private final ProdutoraGateway produtoraGateway;
    private final BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase;
    private final ProdutoraMapper produtoraMapper;

    public AtualizarProdutoraService(ProdutoraGateway produtoraGateway,
                                     BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase,
                                     ProdutoraMapper produtoraMapper) {
        this.produtoraGateway = produtoraGateway;
        this.buscarProdutoraPorIdUseCase = buscarProdutoraPorIdUseCase;
        this.produtoraMapper = produtoraMapper;
    }

    @Transactional
    public ProdutoraResponse execute(Long id, ProdutoraRequest produtoraRequest) {
        Produtora produtora = buscarProdutoraPorIdUseCase.buscarProdutora(id);

        if (!produtora.getNome().equals(produtoraRequest.nome()) && produtoraGateway.existsByNome(produtoraRequest.nome())) {
            throw new RuntimeException("Ja existe uma produtora cadastrada com este nome.");
        }

        produtoraMapper.converterDTOParaEntity(produtora, produtoraRequest);

        return produtoraMapper.converterEntityParaDTO(produtoraGateway.save(produtora));
    }
}



