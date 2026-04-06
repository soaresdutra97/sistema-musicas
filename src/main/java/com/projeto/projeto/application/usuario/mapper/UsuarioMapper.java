package com.projeto.projeto.application.usuario.mapper;

import com.projeto.projeto.application.usuario.dto.UsuarioRequest;
import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.domain.usuario.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioMapper {

    public UsuarioResponse converterEntityParaDTO(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataInscricao()
        );
    }

    public void converterDTOParaEntity(Usuario usuario, UsuarioRequest usuarioRequest) {
        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());
        usuario.setDataInscricao(usuarioRequest.dataInscricao() != null ? usuarioRequest.dataInscricao() : LocalDate.now());
    }
}



