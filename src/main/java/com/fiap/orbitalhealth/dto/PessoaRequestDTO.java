package com.fiap.orbitalhealth.dto;

import com.fiap.orbitalhealth.enums.TipoAmbiente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Idade é obrigatória")
        @Min(value = 0, message = "Idade inválida")
        Integer idade,

        @NotNull(message = "Tipo ambiente é obrigatório")
        TipoAmbiente tipoAmbiente,

        String condicaoSaude,

        String contatoEmergencia,

        String cidade,

        String estado,

        String pais

) {
}
