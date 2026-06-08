package com.fiap.orbitalhealth.dto;

import jakarta.validation.constraints.NotNull;

public record RegistroSaudeRequestDTO(

        @NotNull
        Double temperatura,

        @NotNull
        Integer frequenciaCardiaca,

        @NotNull
        String pressaoArterial,

        @NotNull
        Integer oxigenacao,

        @NotNull
        Integer nivelDor,

        @NotNull
        Double hidratacao,

        @NotNull
        Integer horasSono,

        String sintomas,

        @NotNull
        Long pessoaId

) {
}
