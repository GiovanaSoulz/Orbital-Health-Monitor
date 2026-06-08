package com.fiap.orbitalhealth.repository;

import com.fiap.orbitalhealth.entity.PessoaMonitorada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaMonitoradaRepository
        extends JpaRepository<PessoaMonitorada, Long> {
}