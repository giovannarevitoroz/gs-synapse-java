package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.RecomendacaoSaude;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoSaudeRepository extends JpaRepository<RecomendacaoSaude, Long> {
    Page<RecomendacaoSaude> findAll(Pageable pageable);
}
