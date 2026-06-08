package com.fiap.orbitalhealth.dto;

public record MedicamentoPacienteRequestDTO(

        Long pessoaId,

        Long medicamentoId,

        String frequenciaUso,

        String observacao

) {
}