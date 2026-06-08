package com.fiap.orbitalhealth.dto;

import com.fiap.orbitalhealth.enums.TipoAmbiente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class PessoaResponseDTO
        extends RepresentationModel<PessoaResponseDTO> {

    private Long id;

    private String nome;

    private Integer idade;

    private TipoAmbiente tipoAmbiente;

    private String condicaoSaude;

    private String contatoEmergencia;

    private String cidade;

    private String estado;

    private String pais;

    public PessoaResponseDTO(
            Long id,
            String nome,
            Integer idade,
            TipoAmbiente tipoAmbiente,
            String condicaoSaude,
            String contatoEmergencia,
            String cidade,
            String estado,
            String pais) {

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipoAmbiente = tipoAmbiente;
        this.condicaoSaude = condicaoSaude;
        this.contatoEmergencia = contatoEmergencia;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }
}