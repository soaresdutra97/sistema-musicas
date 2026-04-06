package com.projeto.projeto.infrastructure.adapter.outbound.persistence.musica.gateway;

import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.infrastructure.adapter.outbound.persistence.musica.repository.MusicaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MusicaJpaGateway implements MusicaGateway {

    private final MusicaRepository musicaRepository;

    public MusicaJpaGateway(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    @Override
    public Musica save(Musica musica) {
        return musicaRepository.save(musica);
    }

    @Override
    public Optional<Musica> findById(Long id) {
        return musicaRepository.findById(id);
    }

    @Override
    public List<Musica> findAll() {
        return musicaRepository.findAll();
    }

    @Override
    public void delete(Musica musica) {
        musicaRepository.delete(musica);

    }

    @Override
    public boolean existsById(Long id) {
        return musicaRepository.existsById(id);
    }

    @Override
    public boolean existsByNome(String nome) {
        return musicaRepository.existsByNome(nome);
    }
}




