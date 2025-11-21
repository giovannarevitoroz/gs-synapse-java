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
        model.addAttribute("tituloPagina", "Dashboard");
        // O fragmento deve ser incluído via Thymeleaf no layout principal
        model.addAttribute("conteudo", "fragments/home :: homeFragment");
        return "home";
    }

    @GetMapping("/")
    public String root() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se a autenticação existe e se o principal não é o 'anonymousUser' padrão
        if (auth != null && auth.isAuthenticated() &&
                !auth.getPrincipal().equals("anonymousUser")) {

            // Usuário autenticado: ir para a home
            return "redirect:/home";
        } else {
            // Usuário NÃO autenticado: ir direto para o login
            return "redirect:/login";
        }
    }

    // Os outros métodos de redirecionamento permanecem iguais:

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