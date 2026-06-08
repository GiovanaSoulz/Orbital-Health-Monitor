package com.fiap.orbitalhealth.service;

import com.fiap.orbitalhealth.entity.Alerta;
import com.fiap.orbitalhealth.entity.AlertaCritico;
import com.fiap.orbitalhealth.entity.AlertaModerado;
import com.fiap.orbitalhealth.repository.AlertaRepository;
import com.fiap.orbitalhealth.entity.Alerta;
import com.fiap.orbitalhealth.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository repository;

    public Alerta salvar(Alerta alerta) {

        return repository.save(alerta);
    }

    public List<Alerta> listarTodos() {

        return repository.findAll();
    }

    public Alerta buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Alerta não encontrado"));
    }

    public void deletar(Long id) {

        repository.deleteById(id);
    }
}