package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.config.JwtUtil;
import com.fiap.gs_synapse.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO dto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getNomeUsuario(), dto.getSenhaUsuario())
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuário ou senha inválidos"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getNomeUsuario());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("jwt", jwt));
    }
}
