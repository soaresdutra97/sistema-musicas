package com.projeto.projeto.application.estilo.dto;

import jakarta.validation.constraints.NotNull;

public record EstiloRequest(

        @NotNull(message = "Campo nome e obrigatorio")
        String nome

) {
}

