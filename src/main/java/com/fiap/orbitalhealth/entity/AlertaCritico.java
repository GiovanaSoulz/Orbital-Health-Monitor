package com.fiap.orbitalhealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_alerta_critico")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlertaCritico extends Alerta {

    private String acaoEmergencial;
}