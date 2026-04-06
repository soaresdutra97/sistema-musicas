package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;

public interface RemoverFavoritasPorUsuarioUseCase {
    void execute(Long usuarioId);
}
