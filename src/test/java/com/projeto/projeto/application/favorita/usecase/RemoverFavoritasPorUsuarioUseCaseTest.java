package com.projeto.projeto.application.favorita.usecase;

import com.projeto.projeto.application.favorita.port.out.FavoritaGateway;
import com.projeto.projeto.application.usuario.usecase.BuscarUsuarioPorIdService;
import com.projeto.projeto.application.usuario.mapper.UsuarioMapper;
import com.projeto.projeto.application.usuario.port.out.UsuarioGateway;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import com.projeto.projeto.domain.favorita.model.Favorita;
import com.projeto.projeto.domain.usuario.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoverFavoritasPorUsuarioUseCaseTest {

    @Test
    void deveRemoverTodasAsFavoritasDeUmUsuarioExistente() {
        FakeFavoritaGateway favoritaGateway = new FakeFavoritaGateway();
        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdService(
                new FakeUsuarioGateway(Optional.of(new Usuario(1L, "JoÃ£o Silva", "joao@example.com", LocalDate.now(), List.of()))),
                new UsuarioMapper()
        );
        RemoverFavoritasPorUsuarioService useCase = new RemoverFavoritasPorUsuarioService(
                favoritaGateway,
                buscarUsuarioPorIdUseCase
        );

        useCase.execute(1L);

        assertTrue(favoritaGateway.deleteByUsuarioIdCalled);
        assertEquals(1L, favoritaGateway.deletedUsuarioId);
    }

    @Test
    void naoDeveRemoverFavoritasQuandoUsuarioNaoExiste() {
        FakeFavoritaGateway favoritaGateway = new FakeFavoritaGateway();
        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase = new BuscarUsuarioPorIdService(
                new FakeUsuarioGateway(Optional.empty()),
                new UsuarioMapper()
        );
        RemoverFavoritasPorUsuarioService useCase = new RemoverFavoritasPorUsuarioService(
                favoritaGateway,
                buscarUsuarioPorIdUseCase
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(99L));
        assertFalse(favoritaGateway.deleteByUsuarioIdCalled);
    }

    private static class FakeFavoritaGateway implements FavoritaGateway {
        private boolean deleteByUsuarioIdCalled;
        private Long deletedUsuarioId;

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
            return Optional.empty();
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
        }

        @Override
        public void deleteByUsuarioId(Long usuarioId) {
            this.deleteByUsuarioIdCalled = true;
            this.deletedUsuarioId = usuarioId;
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
        private final Optional<Usuario> usuario;

        private FakeUsuarioGateway(Optional<Usuario> usuario) {
            this.usuario = usuario;
        }

        @Override
        public Usuario save(Usuario usuario) {
            return usuario;
        }

        @Override
        public Optional<Usuario> findById(Long id) {
            return usuario;
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
            return usuario.isPresent();
        }

        @Override
        public boolean existsByEmail(String email) {
            return false;
        }
    }
}


