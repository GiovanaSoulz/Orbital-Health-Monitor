package com.fiap.orbitalhealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_alerta_moderado")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlertaModerado extends Alerta {

    private String recomendacao;
}