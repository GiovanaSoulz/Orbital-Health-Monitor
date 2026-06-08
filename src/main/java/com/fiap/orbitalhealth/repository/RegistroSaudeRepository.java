package com.fiap.orbitalhealth.repository;

import com.fiap.orbitalhealth.entity.RegistroSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSaudeRepository
        extends JpaRepository<RegistroSaude, Long> {
}