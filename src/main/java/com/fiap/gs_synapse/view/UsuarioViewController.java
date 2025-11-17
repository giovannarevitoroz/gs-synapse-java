package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    // Tela inicial / dashboard
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // Tela de login
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "login";
    }

    // Rotas para fácil navegação no menu
    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/competencias")
    public String competencias() {
        return "redirect:/competencias/listar";
    }

    // 🛠️ MANTIDO: Rota de entrada que REDIRECIONA para a rota de listagem específica
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
    public String bemEstar() {
        return "redirect:/bemestar/listar";
    }
}