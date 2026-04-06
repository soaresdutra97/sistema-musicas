package com.projeto.projeto.application.artista.mapper;

// Mapper de Album usado para transformar Album (entidade) em AlbumResponse (DTO).
import com.projeto.projeto.application.album.mapper.AlbumMapper;
// DTO de resposta para itens de album vinculados ao artista.
import com.projeto.projeto.application.album.dto.AlbumResponse;
// DTO de entrada com dados para criar/atualizar artista.
import com.projeto.projeto.application.artista.dto.ArtistaRequest;
// DTO de saida com os dados consolidados do artista.
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
// DTO de resposta para itens de musica vinculados ao artista.
import com.projeto.projeto.application.musica.dto.MusicaResponse;
// Mapper de Musica usado para transformar Musica (entidade) em MusicaResponse (DTO).
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
// Entidade de dominio Artista, fonte dos dados persistidos.
import com.projeto.projeto.domain.artista.model.Artista;
// Marca a classe para ser gerenciada pelo Spring e injetada onde necessario.
import org.springframework.stereotype.Component;

// Tipo de colecao usado nos campos do response.
import java.util.List;

// Registra este mapper como bean do Spring.
@Component
public class ArtistaMapper {

    // Dependencia para mapear cada musica da entidade para DTO.
    private final MusicaMapper musicaMapper;
    // Dependencia para mapear cada album da entidade para DTO.
    private final AlbumMapper albumMapper;

    // Injecao por construtor das dependencias necessarias para os mapeamentos aninhados.
    public ArtistaMapper(MusicaMapper musicaMapper, AlbumMapper albumMapper) {
        // Guarda o mapper de musica para uso no metodo converterEntityParaDTO.
        this.musicaMapper = musicaMapper;
        // Guarda o mapper de album para uso no metodo converterEntityParaDTO.
        this.albumMapper = albumMapper;
    }

    // Converte a entidade Artista para o DTO ArtistaResponse.
    public ArtistaResponse converterEntityParaDTO(Artista artista) {

        // Se a lista de musicas for nula, retorna lista vazia; senao, mapeia cada Musica para MusicaResponse.
        List<MusicaResponse> musicasLancadas = artista.getMusicasLancadas() == null
                ? List.of()
                : artista.getMusicasLancadas().stream()
                    // Mapeia item a item usando o MusicaMapper.
                    .map(musicaMapper::converterEntityParaDTO)
                    // Coleta o resultado final em uma lista imutavel.
                    .toList();

        // Se a lista de albuns for nula, retorna lista vazia; senao, mapeia cada Album para AlbumResponse.
        List<AlbumResponse> albunsLancados = artista.getAlbunsLancados() == null
                ? List.of()
                : artista.getAlbunsLancados().stream()
                    // Mapeia item a item usando o AlbumMapper.
                    .map(albumMapper::converterEntityParaDTO)
                    // Coleta o resultado final em uma lista imutavel.
                    .toList();

        // Monta e retorna o DTO final com id, nome, musicas e albuns do artista.
        return new ArtistaResponse(
                // Identificador unico do artista.
                artista.getId(),
                // Nome do artista.
                artista.getNome(),
                // Lista de musicas ja convertida para DTO.
                musicasLancadas,
                // Lista de albuns ja convertida para DTO.
                albunsLancados
        );


    }

    // Converte o DTO de entrada para a entidade Artista (atualizacao de campos permitidos).
    public void converterDTOParaEntity(Artista artista, ArtistaRequest artistaRequest) {
        // Atualiza somente o nome, pois o request atual possui apenas este campo.
        artista.setNome(artistaRequest.nome());
    }
}


