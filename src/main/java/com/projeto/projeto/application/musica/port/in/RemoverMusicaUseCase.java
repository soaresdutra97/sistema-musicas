package com.projeto.projeto.application.musica.port.in;

import com.projeto.projeto.application.musica.port.out.MusicaGateway;

public interface RemoverMusicaUseCase {
    void execute(Long id);
}
