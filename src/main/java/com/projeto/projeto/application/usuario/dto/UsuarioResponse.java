package com.projeto.projeto.application.usuario.dto;

import java.time.LocalDate;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        LocalDate dataInscricao
) {
}


