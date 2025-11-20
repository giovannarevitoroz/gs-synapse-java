package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.Locale;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute UsuarioDTO dto,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Locale locale) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("usuarioDTO", dto);
            redirectAttributes.addFlashAttribute("errorMessage", "Preencha todos os campos corretamente.");
            return "redirect:/login";
        }

        if (usuarioRepository.findByNomeUsuario(dto.getNomeUsuario()).isPresent()) {
            redirectAttributes.addFlashAttribute("usuarioDTO", dto);
            redirectAttributes.addFlashAttribute("errorMessage", "Nome de usuário já está em uso.");
            return "redirect:/login";
        }

        // Converte DTO para Entity
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario(dto.getNomeUsuario());
        novoUsuario.setSenhaUsuario(passwordEncoder.encode(dto.getSenhaUsuario()));
        novoUsuario.setAreaAtual(dto.getAreaAtual());
        novoUsuario.setAreaInteresse(dto.getAreaInteresse());
        novoUsuario.setObjetivoCarreira(dto.getObjetivoCarreira());
        novoUsuario.setNivelExperiencia(dto.getNivelExperiencia());

        // Define ROLE padrão
        novoUsuario.setRole("ROLE_USER");

        usuarioRepository.save(novoUsuario);

        redirectAttributes.addFlashAttribute("successMessage", "Conta criada com sucesso! Faça login.");
        return "redirect:/login";
    }
}
