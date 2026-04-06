package com.projeto.projeto.application.musica.usecase;

import com.projeto.projeto.application.musica.port.in.*;


import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RemoverMusicaService implements RemoverMusicaUseCase {

    private final MusicaGateway musicaGateway;
    private final BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase;


    public RemoverMusicaService(MusicaGateway musicaGateway, BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase) {
        this.musicaGateway = musicaGateway;
        this.buscarMusicaPorIdUseCase = buscarMusicaPorIdUseCase;
    }

    @Transactional
    public void execute(Long id) {
        musicaGateway.delete(buscarMusicaPorIdUseCase.buscarMusica(id));
    }

}
