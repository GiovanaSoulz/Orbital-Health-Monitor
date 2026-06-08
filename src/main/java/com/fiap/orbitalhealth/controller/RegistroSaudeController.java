package com.fiap.orbitalhealth.controller;

import com.fiap.orbitalhealth.dto.RegistroSaudeRequestDTO;
import com.fiap.orbitalhealth.dto.RegistroSaudeResponseDTO;
import com.fiap.orbitalhealth.service.RegistroSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@RequiredArgsConstructor
@Tag(
        name = "Registros de Saúde",
        description = "Gerenciamento dos registros clínicos dos pacientes monitorados"
)
public class RegistroSaudeController {

    private final RegistroSaudeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cadastrar registro de saúde",
            description = "Cria um novo registro de saúde para uma pessoa monitorada"
    )
    public RegistroSaudeResponseDTO cadastrar(
            @RequestBody @Valid RegistroSaudeRequestDTO dto) {

        return service.cadastrar(dto);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os registros",
            description = "Retorna todos os registros de saúde cadastrados"
    )
    public List<RegistroSaudeResponseDTO> listarTodos() {

        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar registro por ID",
            description = "Retorna um registro específico através do ID"
    )
    public RegistroSaudeResponseDTO buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar registro",
            description = "Atualiza os dados de um registro de saúde existente"
    )
    public RegistroSaudeResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RegistroSaudeRequestDTO dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Excluir registro",
            description = "Remove um registro de saúde do sistema"
    )
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}