package com.fiap.orbitalhealth.dto;

import com.fiap.orbitalhealth.enums.RiscoSaude;

import java.time.LocalDateTime;

public record RegistroSaudeResponseDTO(

        Long id,

        Double temperatura,

        Integer frequenciaCardiaca,

        String pressaoArterial,

        Integer oxigenacao,

        Integer nivelDor,

        Double hidratacao,

        Integer horasSono,

        String sintomas,

        LocalDateTime dataRegistro,

        Long pessoaId,

        RiscoSaude risco

) {
}