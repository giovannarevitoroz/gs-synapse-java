package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    Page<Competencia> findAll(Pageable pageable);
}
