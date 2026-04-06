package com.projeto.projeto.application.produtora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProdutoraRequest(
        @NotBlank(message = "O nome da produtora e obrigatorio.")
        @Size(max = 150, message = "O nome da produtora deve ter no maximo 150 caracteres.")
        String nome
) {
}


