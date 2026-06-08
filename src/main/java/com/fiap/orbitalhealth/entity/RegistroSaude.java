package com.fiap.orbitalhealth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registro_saude")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;

    private Integer frequenciaCardiaca;

    private String pressaoArterial;

    private Integer oxigenacao;

    private Integer nivelDor;

    private Double hidratacao;

    private Integer horasSono;

    private String sintomas;

    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaMonitorada pessoaMonitorada;
}
