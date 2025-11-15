package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.Recomendacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {
    Page<Recomendacao> findAll(Pageable pageable);
}
