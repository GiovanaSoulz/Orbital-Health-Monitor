package com.fiap.orbitalhealth.service;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.fiap.orbitalhealth.controller.PessoaMonitoradaController;
import com.fiap.orbitalhealth.dto.PessoaRequestDTO;
import com.fiap.orbitalhealth.dto.PessoaResponseDTO;
import com.fiap.orbitalhealth.entity.Endereco;
import com.fiap.orbitalhealth.entity.PessoaMonitorada;
import com.fiap.orbitalhealth.exception.ResourceNotFoundException;
import com.fiap.orbitalhealth.repository.PessoaMonitoradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class PessoaMonitoradaService {

    private final PessoaMonitoradaRepository repository;

    public PessoaResponseDTO cadastrar(PessoaRequestDTO dto) {

        PessoaMonitorada pessoa = PessoaMonitorada.builder()
                .nome(dto.nome())
                .idade(dto.idade())
                .tipoAmbiente(dto.tipoAmbiente())
                .condicaoSaude(dto.condicaoSaude())
                .contatoEmergencia(dto.contatoEmergencia())
                .endereco(
                        Endereco.builder()
                                .cidade(dto.cidade())
                                .estado(dto.estado())
                                .pais(dto.pais())
                                .build()
                )
                .build();

        repository.save(pessoa);

        return converterParaResponse(pessoa);
    }

    public List<PessoaResponseDTO> listarTodas() {

        return repository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public PessoaResponseDTO buscarPorId(Long id) {

        PessoaMonitorada pessoa = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pessoa não encontrada"));

        return converterParaResponse(pessoa);
    }

    public PessoaResponseDTO atualizar(
            Long id,
            PessoaRequestDTO dto) {

        PessoaMonitorada pessoa = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pessoa não encontrada"));

        pessoa.setNome(dto.nome());
        pessoa.setIdade(dto.idade());
        pessoa.setTipoAmbiente(dto.tipoAmbiente());
        pessoa.setCondicaoSaude(dto.condicaoSaude());
        pessoa.setContatoEmergencia(dto.contatoEmergencia());

        pessoa.setEndereco(
                Endereco.builder()
                        .cidade(dto.cidade())
                        .estado(dto.estado())
                        .pais(dto.pais())
                        .build()
        );

        repository.save(pessoa);

        return converterParaResponse(pessoa);
    }

    public void deletar(Long id) {

        PessoaMonitorada pessoa = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pessoa não encontrada"));

        repository.delete(pessoa);
    }

    private PessoaResponseDTO converterParaResponse(
            PessoaMonitorada pessoa) {

        PessoaResponseDTO response =
                new PessoaResponseDTO(

                        pessoa.getId(),
                        pessoa.getNome(),
                        pessoa.getIdade(),
                        pessoa.getTipoAmbiente(),
                        pessoa.getCondicaoSaude(),
                        pessoa.getContatoEmergencia(),
                        pessoa.getEndereco() != null ?
                                pessoa.getEndereco().getCidade() : null,
                        pessoa.getEndereco() != null ?
                                pessoa.getEndereco().getEstado() : null,
                        pessoa.getEndereco() != null ?
                                pessoa.getEndereco().getPais() : null

                );

        response.add(

                linkTo(
                        methodOn(
                                PessoaMonitoradaController.class)
                                .buscarPorId(
                                        pessoa.getId()))
                        .withSelfRel()

        );

        response.add(

                linkTo(
                        methodOn(
                                PessoaMonitoradaController.class)
                                .listarTodas())
                        .withRel("listar")

        );

        return response;
} }
