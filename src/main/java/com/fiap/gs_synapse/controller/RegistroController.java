package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Locale;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute UsuarioDTO dto, Locale locale) {
        // Usa o método que criptografa a senha
        usuarioService.criarUsuario(dto, locale);
        return "redirect:/login?created=true";
    }
}