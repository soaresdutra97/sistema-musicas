package com.projeto.projeto.application.artista.port.in;

import com.projeto.projeto.application.artista.port.out.ArtistaGateway;

public interface RemoverArtistaUseCase {
    void execute(Long id);
}
