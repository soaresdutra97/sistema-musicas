package com.projeto.projeto.infrastructure.adapter.outbound.persistence.estilo.gateway;

import com.projeto.projeto.application.estilo.port.out.EstiloGateway;
import com.projeto.projeto.domain.estilo.model.Estilo;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.estilo.repository.EstiloRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstiloJpaGateway implements EstiloGateway {

    private final EstiloRepository estiloRepository;

    public EstiloJpaGateway(EstiloRepository estiloRepository) {
        this.estiloRepository = estiloRepository;
    }

    @Override
    public Estilo save(Estilo estilo) {
        return estiloRepository.save(estilo);
    }

    @Override
    public Optional<Estilo> findById(Long id) {
        return estiloRepository.findById(id);
    }

    @Override
    public List<Estilo> findAll() {
        return estiloRepository.findAll();
    }

    @Override
    public void delete(Estilo estilo) {
        estiloRepository.delete(estilo);

    }

    @Override
    public boolean existsById(Long id) {
        return estiloRepository.existsById(id);
    }

    @Override
    public boolean existsByNome(String nome) {
        return estiloRepository.existsByNome(nome);
    }

}



