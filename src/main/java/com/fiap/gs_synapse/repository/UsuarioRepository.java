package com.fiap.gs_synapse.repository;

import com.fiap.gs_synapse.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Página de usuários
    Page<Usuario> findAll(Pageable pageable);

    // Buscar usuário pelo nome (necessário para autenticação JWT)
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
