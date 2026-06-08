package com.fiap.orbitalhealth.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoPacienteId
        implements Serializable {

    private Long pessoaId;

    private Long medicamentoId;
}