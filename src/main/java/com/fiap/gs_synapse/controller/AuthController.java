package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.service.CustomUserDetailsService;
import com.fiap.gs_synapse.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO dto) {
        try {
            // Tenta autenticar o usuário com Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getNomeUsuario(),
                            dto.getSenhaUsuario()
                    )
            );
        } catch (BadCredentialsException e) {
            // Senha ou usuário inválido
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Usuário ou senha inválidos"));
        }

        // Carrega os detalhes do usuário
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getNomeUsuario());

        // Gera JWT
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Retorna token
        return ResponseEntity.ok(Map.of(
                "jwt", jwt,
                "username", userDetails.getUsername()
        ));
    }
}
