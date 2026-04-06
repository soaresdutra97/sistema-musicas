package com.projeto.projeto.application.artista.port.in;

import com.projeto.projeto.application.artista.mapper.ArtistaMapper;
import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.application.artista.dto.ArtistaRequest;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
import com.projeto.projeto.domain.artista.model.Artista;

public interface AtualizarArtistaUseCase {
    ArtistaResponse execute(Long id, ArtistaRequest artistaRequest);
}
