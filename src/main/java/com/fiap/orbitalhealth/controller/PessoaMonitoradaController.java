package com.fiap.orbitalhealth.controller;

import com.fiap.orbitalhealth.dto.PessoaRequestDTO;
import com.fiap.orbitalhealth.dto.PessoaResponseDTO;
import com.fiap.orbitalhealth.service.PessoaMonitoradaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
@Tag(
        name = "Pessoas Monitoradas",
        description = "Gerenciamento das pessoas acompanhadas pelo sistema"
)
public class PessoaMonitoradaController {

    private final PessoaMonitoradaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cadastrar pessoa monitorada"
    )
    public PessoaResponseDTO cadastrar(
            @RequestBody @Valid PessoaRequestDTO dto) {

        return service.cadastrar(dto);
    }

    @Operation(
            summary = "Listar todas as pessoas monitoradas"
    )
    @GetMapping
    public List<PessoaResponseDTO> listarTodas() {

        return service.listarTodas();
    }

    @Operation(
            summary = "Buscar pessoa por ID"
    )
    @GetMapping("/{id}")
    public PessoaResponseDTO buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @Operation(
            summary = "Atualizar pessoa monitorada"
    )
    @PutMapping("/{id}")
    public PessoaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PessoaRequestDTO dto) {

        return service.atualizar(id, dto);
    }

    @Operation(
            summary = "Excluir pessoa monitorada"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}