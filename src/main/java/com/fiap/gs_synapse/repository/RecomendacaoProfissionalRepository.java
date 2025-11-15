package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.RecomendacaoProfissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoProfissionalRepository extends JpaRepository<RecomendacaoProfissional, Long> {
    Page<RecomendacaoProfissional> findAll(Pageable pageable);
}
