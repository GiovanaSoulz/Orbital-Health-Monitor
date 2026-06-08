package com.fiap.orbitalhealth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_medicamento_paciente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoPaciente {

    @EmbeddedId
    private MedicamentoPacienteId id;

    @ManyToOne
    @MapsId("pessoaId")
    @JoinColumn(name = "pessoa_id")
    private PessoaMonitorada pessoa;

    @ManyToOne
    @MapsId("medicamentoId")
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    private String frequenciaUso;

    private String observacao;
}