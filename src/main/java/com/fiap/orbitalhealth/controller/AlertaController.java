package com.fiap.orbitalhealth.controller;

import com.fiap.orbitalhealth.entity.Alerta;
import com.fiap.orbitalhealth.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
@Tag(name = "Alertas")
public class AlertaController {

    private final AlertaService service;

    @GetMapping
    @Operation(summary = "Listar alertas")
    public List<Alerta> listarTodos() {

        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar alerta por ID")
    public Alerta buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir alerta")
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}