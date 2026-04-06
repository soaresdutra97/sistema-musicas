package com.projeto.projeto.infrastructure.adapter.outbound.persistence.produtora.gateway;

import com.projeto.projeto.application.produtora.port.out.ProdutoraGateway;
import com.projeto.projeto.domain.produtora.model.Produtora;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.produtora.repository.ProdutoraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoraJpaGateway implements ProdutoraGateway {

    private final ProdutoraRepository produtoraRepository;

    public ProdutoraJpaGateway(ProdutoraRepository produtoraRepository) {
        this.produtoraRepository = produtoraRepository;
    }

    @Override
    public Produtora save(Produtora produtora) {
        return produtoraRepository.save(produtora);
    }

    @Override
    public Optional<Produtora> findById(Long id) {
        return produtoraRepository.findById(id);
    }

    @Override
    public List<Produtora> findAll() {
        return produtoraRepository.findAll();
    }

    @Override
    public void delete(Produtora produtora) {
        produtoraRepository.delete(produtora);
    }

    @Override
    public boolean existsById(Long id) {
        return produtoraRepository.existsById(id);
    }

    @Override
    public boolean existsByNome(String nome) {
        return produtoraRepository.existsByNome(nome);
    }
}




