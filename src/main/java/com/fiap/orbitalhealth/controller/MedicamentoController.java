package com.fiap.orbitalhealth.controller;

import com.fiap.orbitalhealth.dto.MedicamentoRequestDTO;
import com.fiap.orbitalhealth.dto.MedicamentoResponseDTO;
import com.fiap.orbitalhealth.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
@Tag(name = "Medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar medicamento")
    public MedicamentoResponseDTO cadastrar(
            @RequestBody @Valid MedicamentoRequestDTO dto) {

        return service.cadastrar(dto);
    }

    @GetMapping
    @Operation(summary = "Listar medicamentos")
    public List<MedicamentoResponseDTO> listarTodos() {

        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar medicamento")
    public MedicamentoResponseDTO buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar medicamento")
    public MedicamentoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid MedicamentoRequestDTO dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir medicamento")
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}