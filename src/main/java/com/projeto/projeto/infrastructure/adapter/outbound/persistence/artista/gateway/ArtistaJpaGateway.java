package com.projeto.projeto.infrastructure.adapter.outbound.persistence.artista.gateway;

import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.domain.artista.model.Artista;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.artista.repository.ArtistaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistaJpaGateway implements ArtistaGateway {

    private final ArtistaRepository artistaRepository;

    public ArtistaJpaGateway(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Override
    public Artista save(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public Optional<Artista> findById(Long id) {
        return artistaRepository.findById(id);
    }

    @Override
    public List<Artista> findAll() {
        return artistaRepository.findAll();
    }

    @Override
    public void delete(Artista artista) {
        artistaRepository.delete(artista);

    }

    @Override
    public boolean existsById(Long id) {
        return artistaRepository.existsById(id);
    }

    @Override
    public boolean existsByNome(String nome) {
        return artistaRepository.existsByNome(nome);
    }
}


