package com.fiap.orbitalhealth.dto;

public record MedicamentoPacienteResponseDTO(

        Long pessoaId,

        Long medicamentoId,

        String nomePaciente,

        String nomeMedicamento,

        String frequenciaUso,

        String observacao

) {
}