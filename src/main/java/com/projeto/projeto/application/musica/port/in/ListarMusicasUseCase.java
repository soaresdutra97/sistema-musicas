package com.projeto.projeto.application.musica.port.in;

import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import java.util.List;

public interface ListarMusicasUseCase {
    List<MusicaResponse> execute();
    List<MusicaResponse> executar();
}
