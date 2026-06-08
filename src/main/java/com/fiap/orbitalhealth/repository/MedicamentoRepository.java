package com.fiap.orbitalhealth.repository;

import com.fiap.orbitalhealth.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository
        extends JpaRepository<Medicamento, Long> {
}