package com.fiap.orbitalhealth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_medicamento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dosagem;

    private String fabricante;
}