package com.fiap.orbitalhealth.entity;

import com.fiap.orbitalhealth.enums.TipoAmbiente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pessoa_monitorada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaMonitorada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    private TipoAmbiente tipoAmbiente;

    private String condicaoSaude;

    private String contatoEmergencia;

    @Embedded
    private Endereco endereco;
}