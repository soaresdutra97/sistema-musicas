package com.projeto.projeto.application.favorita.port.in;

import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.mapper.FavoritaMapper;
import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import java.util.List;

public interface ListarFavoritasUseCase {
    List<FavoritaResponse> execute();
}
