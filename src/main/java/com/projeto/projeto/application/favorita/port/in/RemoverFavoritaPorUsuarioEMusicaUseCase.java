package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.musica.port.in.BuscarMusicaPorIdUseCase;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import com.projeto.projeto.domain.favorita.model.Favorita;

public interface RemoverFavoritaPorUsuarioEMusicaUseCase {
    void execute(Long usuarioId, Long musicaId);
}
