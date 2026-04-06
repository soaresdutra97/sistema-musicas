package com.projeto.projeto.application.artista.port.in;

import com.projeto.projeto.application.artista.mapper.ArtistaMapper;
import com.projeto.projeto.application.artista.port.out.ArtistaGateway;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
import java.util.List;

public interface ListarArtistasUseCase {
    List<ArtistaResponse> execute();
}
