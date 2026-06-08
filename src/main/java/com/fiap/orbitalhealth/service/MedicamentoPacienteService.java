package com.fiap.orbitalhealth.service;

import com.fiap.orbitalhealth.dto.MedicamentoPacienteRequestDTO;
import com.fiap.orbitalhealth.dto.MedicamentoPacienteResponseDTO;
import com.fiap.orbitalhealth.entity.*;
import com.fiap.orbitalhealth.exception.ResourceNotFoundException;
import com.fiap.orbitalhealth.repository.MedicamentoPacienteRepository;
import com.fiap.orbitalhealth.repository.MedicamentoRepository;
import com.fiap.orbitalhealth.repository.PessoaMonitoradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoPacienteService {

    private final MedicamentoPacienteRepository repository;
    private final PessoaMonitoradaRepository pessoaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoPacienteResponseDTO vincular(
            MedicamentoPacienteRequestDTO dto) {

        PessoaMonitorada pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pessoa não encontrada"));

        Medicamento medicamento = medicamentoRepository
                .findById(dto.medicamentoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicamento não encontrado"));

        MedicamentoPaciente vinculo =
                MedicamentoPaciente.builder()
                        .id(
                                new MedicamentoPacienteId(
                                        pessoa.getId(),
                                        medicamento.getId()
                                )
                        )
                        .pessoa(pessoa)
                        .medicamento(medicamento)
                        .frequenciaUso(dto.frequenciaUso())
                        .observacao(dto.observacao())
                        .build();

        repository.save(vinculo);

        return converter(vinculo);
    }

    public List<MedicamentoPacienteResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    private MedicamentoPacienteResponseDTO converter(
            MedicamentoPaciente mp) {

        return new MedicamentoPacienteResponseDTO(
                mp.getPessoa().getId(),
                mp.getMedicamento().getId(),
                mp.getPessoa().getNome(),
                mp.getMedicamento().getNome(),
                mp.getFrequenciaUso(),
                mp.getObservacao()
        );
    }
}