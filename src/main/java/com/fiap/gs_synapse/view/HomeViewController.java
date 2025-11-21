package com.fiap.gs_synapse.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/home")
    public String home(Model model) {
        // Título da página
        model.addAttribute("tituloPagina", "Dashboard");

        // Nome do usuário logado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("usuarioLogado", auth.getName());
        } else {
            model.addAttribute("usuarioLogado", "Visitante");
        }

        // Fragmento principal (se você estiver usando layout e fragments)
        model.addAttribute("conteudo", "fragments/home :: homeFragment");

        return "home";
    }

    @GetMapping("/")
    public String root() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() &&
                !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    // Redirecionamentos rápidos
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
