package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Exibe a página de registro (GET)
    @GetMapping("/registrar")
    public String form(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "registrar"; // página registrar.html
    }

    // Processa o registro (POST)
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute UsuarioDTO dto, Locale locale) {

        usuarioService.criarUsuarioPublico(dto, locale);

        return "redirect:/login?created=true";
    }
}
