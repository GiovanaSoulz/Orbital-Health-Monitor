package com.fiap.orbitalhealth.service;

import com.fiap.orbitalhealth.dto.MedicamentoRequestDTO;
import com.fiap.orbitalhealth.dto.MedicamentoResponseDTO;
import com.fiap.orbitalhealth.entity.Medicamento;
import com.fiap.orbitalhealth.exception.ResourceNotFoundException;
import com.fiap.orbitalhealth.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository repository;

    public MedicamentoResponseDTO cadastrar(
            MedicamentoRequestDTO dto) {

        Medicamento medicamento = Medicamento.builder()
                .nome(dto.nome())
                .dosagem(dto.dosagem())
                .fabricante(dto.fabricante())
                .build();

        repository.save(medicamento);

        return converter(medicamento);
    }

    public List<MedicamentoResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public MedicamentoResponseDTO buscarPorId(Long id) {

        Medicamento medicamento = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicamento não encontrado"));

        return converter(medicamento);
    }

    public MedicamentoResponseDTO atualizar(
            Long id,
            MedicamentoRequestDTO dto) {

        Medicamento medicamento = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicamento não encontrado"));

        medicamento.setNome(dto.nome());
        medicamento.setDosagem(dto.dosagem());
        medicamento.setFabricante(dto.fabricante());

        repository.save(medicamento);

        return converter(medicamento);
    }

    public void deletar(Long id) {

        Medicamento medicamento = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicamento não encontrado"));

        repository.delete(medicamento);
    }

    private MedicamentoResponseDTO converter(
            Medicamento medicamento) {

        return new MedicamentoResponseDTO(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getFabricante()
        );
    }
}