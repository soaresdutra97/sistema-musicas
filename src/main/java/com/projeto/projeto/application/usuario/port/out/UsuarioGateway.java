package com.projeto.projeto.application.usuario.port.out;

import com.projeto.projeto.domain.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    void delete(Usuario usuario);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}


