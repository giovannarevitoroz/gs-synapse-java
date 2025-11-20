package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("tituloPagina", "Dashboard");
        model.addAttribute("conteudo", "fragments/home :: homeFragment");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        // Garante que o objeto usuarioDTO esteja sempre disponível para o formulário de cadastro no home.html
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