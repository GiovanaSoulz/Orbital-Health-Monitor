package com.fiap.orbitalhealth.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicamentoRequestDTO(

        @NotBlank(message = "Nome obrigatório")
        String nome,

        @NotBlank(message = "Dosagem obrigatória")
        String dosagem,

        String fabricante

) {
}