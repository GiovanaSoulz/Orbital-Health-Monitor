package com.fiap.orbitalhealth.repository;

import com.fiap.orbitalhealth.entity.MedicamentoPaciente;
import com.fiap.orbitalhealth.entity.MedicamentoPacienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoPacienteRepository
        extends JpaRepository<
        MedicamentoPaciente,
        MedicamentoPacienteId> {
}