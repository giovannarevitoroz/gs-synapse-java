package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.RegistroBemEstar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroBemEstarRepository extends JpaRepository<RegistroBemEstar, Long> {
    Page<RegistroBemEstar> findAll(Pageable pageable);
}
