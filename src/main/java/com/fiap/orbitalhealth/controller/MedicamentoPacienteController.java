package com.fiap.orbitalhealth.controller;

import com.fiap.orbitalhealth.dto.MedicamentoPacienteRequestDTO;
import com.fiap.orbitalhealth.dto.MedicamentoPacienteResponseDTO;
import com.fiap.orbitalhealth.service.MedicamentoPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos-pacientes")
@RequiredArgsConstructor
@Tag(name = "Medicamentos por Paciente")
public class MedicamentoPacienteController {

    private final MedicamentoPacienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Vincular medicamento ao paciente")
    public MedicamentoPacienteResponseDTO vincular(
            @RequestBody MedicamentoPacienteRequestDTO dto) {

        return service.vincular(dto);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos")
    public List<MedicamentoPacienteResponseDTO> listarTodos() {

        return service.listarTodos();
    }
}