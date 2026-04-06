package com.projeto.projeto.infrastructure.adapter.outbound.persistence.usuario.repository;

import com.projeto.projeto.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
}




