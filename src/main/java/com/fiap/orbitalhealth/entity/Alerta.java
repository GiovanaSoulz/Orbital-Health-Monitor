package com.fiap.orbitalhealth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_alerta")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;
}
