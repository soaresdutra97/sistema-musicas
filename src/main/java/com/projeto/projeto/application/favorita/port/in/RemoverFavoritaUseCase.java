package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;

public interface RemoverFavoritaUseCase {
    void execute(Long id);
}
