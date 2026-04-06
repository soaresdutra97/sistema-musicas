package com.projeto.projeto.application.album.port.in;

import com.projeto.projeto.domain.album.model.Album;
import java.util.LinkedHashSet;
import java.util.Set;

public interface BuscarAlbumPorIdsUseCase {
    Set<Album> execute(Set<Long> ids);
    Set<Album> executar(Set<Long> ids);
}
