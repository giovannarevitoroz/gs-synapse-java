package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class HomeViewController {

    // Ative/desative o mock login aqui
    private static final boolean MOCK_LOGIN_ATIVO = true;

    @GetMapping("/home")
    public String home(Model model) {
        // Mock login: simula um usuário autenticado
        if (MOCK_LOGIN_ATIVO && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    "admin", // nome do usuário simulado
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")) // ou ROLE_USER
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        model.addAttribute("tituloPagina", "Dashboard");
        model.addAttribute("conteudo", "fragments/home :: homeFragment");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("usuarioDTO")) {
            model.addAttribute("usuarioDTO", new UsuarioDTO());
        }
        model.addAttribute("tituloPagina", "Login");
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/competencias")
    public String competencias() {
        return "redirect:/competencias/listar";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/recomendacoes")
    public String recomendacoes() {
        return "redirect:/recomendacoes/listar";
    }

    @GetMapping("/recomendacoes/saude")
    public String recomendacoesSaude() {
        return "redirect:/recomendacoes/saude/listar";
    }

    @GetMapping("/recomendacoes/profissionais")
    public String recomendacoesProfissionais() {
        return "redirect:/recomendacoes/profissionais/listar";
    }

    @GetMapping("/bemestar")
    public String bemestar() {
        return "redirect:/bemestar/listar";
    }
}
