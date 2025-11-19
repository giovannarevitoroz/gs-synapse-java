package com.fiap.gs_synapse.security;

import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // GARANTE QUE O ROLE SEMPRE COMEÇA COM ROLE_
        String role = usuario.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new User(
                usuario.getNomeUsuario(),
                usuario.getSenhaUsuario(),
                Collections.singletonList(authority)
        );
    }
}
