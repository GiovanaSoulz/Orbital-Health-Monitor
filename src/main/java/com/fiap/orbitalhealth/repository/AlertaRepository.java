package com.fiap.orbitalhealth.repository;

import com.fiap.orbitalhealth.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository
        extends JpaRepository<Alerta, Long> {
}