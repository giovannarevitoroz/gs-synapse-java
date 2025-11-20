package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tenta carregar o usuário pelo nome de usuário
        Usuario usuario = usuarioRepository.findByNomeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Obtém e limpa o papel (ROLE) do usuário
        String role = usuario.getRole() != null ? usuario.getRole().trim() : "USER"; // padrão USER se null

        // Adiciona "ROLE_" se necessário
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        // 🔹 LOG PARA DEBUG
        logger.info("Usuário Autenticado: {} | Role Carregada: {}", usuario.getNomeUsuario(), role);

        // Retorna o objeto UserDetails com as informações do usuário e suas autoridades (papéis)
        return new User(
                usuario.getNomeUsuario(),       // username
                usuario.getSenhaUsuario(),      // senha criptografada
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
