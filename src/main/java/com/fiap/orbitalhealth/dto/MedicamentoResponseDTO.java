package com.fiap.orbitalhealth.dto;

public record MedicamentoResponseDTO(

        Long id,

        String nome,

        String dosagem,

        String fabricante

) {
}