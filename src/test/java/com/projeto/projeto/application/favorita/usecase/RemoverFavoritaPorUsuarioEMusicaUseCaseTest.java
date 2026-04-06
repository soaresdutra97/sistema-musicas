package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.musica.mapper.MusicaMapper;
import com.projeto.projeto.application.musica.port.out.MusicaGateway;
import com.projeto.projeto.application.musica.usecase.BuscarMusicaPorIdService;
import com.projeto.projeto.application.musica.port.in.BuscarMusicaPorIdUseCase;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import com.projeto.projeto.application.usuario.usecase.BuscarUsuarioPorIdService;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import com.projeto.projeto.domain.favorita.model.Favorita;
import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.domain.usuario.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoverFavoritaPorUsuarioEMusicaUseCaseTest {

    @Test
    void deveRemoverFavoritaEspecificaQuandoUsuarioEMusicaExistem() {
        Favorita favorita = new Favorita();
        favorita.setId(10L);

        FakeFavoritaGateway favoritaGateway = new FakeFavoritaGateway(favorita);
        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdService(
                new FakeUsuarioGateway(new Usuario(1L, "JoÃ£o Silva", "joao@example.com", LocalDate.now(), List.of())),
                new UsuarioMapper()
        );
        BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase = new BuscarMusicaPorIdService(
                new FakeMusicaGateway(criarMusica(2L, "Bohemian Rhapsody")),
                new MusicaMapper()
        );
        RemoverFavoritaPorUsuarioEMusicaService useCase = new RemoverFavoritaPorUsuarioEMusicaService(
                favoritaGateway,
                buscarUsuarioPorIdUseCase,
                buscarMusicaPorIdUseCase
        );

        useCase.execute(1L, 2L);

        assertTrue(favoritaGateway.deleteCalled);
        assertEquals(10L, favoritaGateway.deletedFavoritaId);
    }

    @Test
    void naoDeveRemoverQuandoFavoritaNaoExisteParaUsuarioEMusica() {
        FakeFavoritaGateway favoritaGateway = new FakeFavoritaGateway(null);
        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdService(
                new FakeUsuarioGateway(new Usuario(1L, "JoÃ£o Silva", "joao@example.com", LocalDate.now(), List.of())),
                new UsuarioMapper()
        );
        BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase = new BuscarMusicaPorIdService(
                new FakeMusicaGateway(criarMusica(2L, "Bohemian Rhapsody")),
                new MusicaMapper()
        );
        RemoverFavoritaPorUsuarioEMusicaService useCase = new RemoverFavoritaPorUsuarioEMusicaService(
                favoritaGateway,
                buscarUsuarioPorIdUseCase,
                buscarMusicaPorIdUseCase
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(1L, 2L));
        assertFalse(favoritaGateway.deleteCalled);
    }

    @Test
    void naoDeveRemoverQuandoMusicaNaoExiste() {
        FakeFavoritaGateway favoritaGateway = new FakeFavoritaGateway(null);
        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdService(
                new FakeUsuarioGateway(new Usuario(1L, "JoÃ£o Silva", "joao@example.com", LocalDate.now(), List.of())),
                new UsuarioMapper()
        );
        BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase = new BuscarMusicaPorIdService(
                new FakeMusicaGateway(null),
                new MusicaMapper()
        );
        RemoverFavoritaPorUsuarioEMusicaService useCase = new RemoverFavoritaPorUsuarioEMusicaService(
                favoritaGateway,
                buscarUsuarioPorIdUseCase,
                buscarMusicaPorIdUseCase
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(1L, 999L));
        assertFalse(favoritaGateway.deleteCalled);
    }

    private static Musica criarMusica(Long id, String nome) {
        Musica musica = new Musica();
        musica.setId(id);
        musica.setNome(nome);
        musica.setDataLancamento(LocalDate.of(1975, 10, 31));
        musica.setVezesReproduzida(0);
        musica.setVezesFavoritada(0);
        musica.setNumeroCurtidas(0);
        return musica;
    }

    private static class FakeFavoritaGateway implements FavoritaGateway {
        private final Favorita favorita;
        private boolean deleteCalled;
        private Long deletedFavoritaId;

        private FakeFavoritaGateway(Favorita favorita) {
            this.favorita = favorita;
        }

        @Override
        public Favorita save(Favorita favorita) {
            return favorita;
        }

        @Override
        public Optional<Favorita> findById(Long id) {
            return Optional.empty();
        }

        @Override
        public Optional<Favorita> findByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId) {
            return Optional.ofNullable(favorita);
        }

        @Override
        public List<Favorita> findAll() {
            return List.of();
        }

        @Override
        public List<Favorita> findByUsuarioId(Long usuarioId) {
            return List.of();
        }

        @Override
        public void delete(Favorita favorita) {
            this.deleteCalled = true;
            this.deletedFavoritaId = favorita.getId();
        }

        @Override
        public void deleteByUsuarioId(Long usuarioId) {
        }

        @Override
        public boolean existsById(Long id) {
            return false;
        }

        @Override
        public boolean existsByUsuarioIdAndMusicaId(Long usuarioId, Long musicaId) {
            return false;
        }
    }

    private static class FakeUsuarioGateway implements UsuarioGateway {
        private final Usuario usuario;

        private FakeUsuarioGateway(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public Usuario save(Usuario usuario) {
            return usuario;
        }

        @Override
        public Optional<Usuario> findById(Long id) {
            return Optional.ofNullable(usuario);
        }

        @Override
        public List<Usuario> findAll() {
            return List.of();
        }

        @Override
        public void delete(Usuario usuario) {
        }

        @Override
        public boolean existsById(Long id) {
            return usuario != null && usuario.getId().equals(id);
        }

        @Override
        public boolean existsByEmail(String email) {
            return false;
        }
    }

    private static class FakeMusicaGateway implements MusicaGateway {
        private final Musica musica;

        private FakeMusicaGateway(Musica musica) {
            this.musica = musica;
        }

        @Override
        public Musica save(Musica musica) {
            return musica;
        }

        @Override
        public Optional<Musica> findById(Long id) {
            return Optional.ofNullable(musica).filter(value -> value.getId().equals(id));
        }

        @Override
        public List<Musica> findAll() {
            return List.of();
        }

        @Override
        public void delete(Musica musica) {
        }

        @Override
        public boolean existsById(Long id) {
            return musica != null && musica.getId().equals(id);
        }

        @Override
        public boolean existsByNome(String nome) {
            return false;
        }
    }
}


