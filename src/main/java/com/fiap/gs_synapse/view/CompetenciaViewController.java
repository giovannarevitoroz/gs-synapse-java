package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.CompetenciaDTO;
import com.fiap.gs_synapse.service.CompetenciaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competencias") // Rota base: /competencias
public class CompetenciaViewController {

    private final CompetenciaService service;

    public CompetenciaViewController(CompetenciaService service) {
        this.service = service;
    }

    // LISTAR COMPETÊNCIAS
    @GetMapping("/listar")
    public String listar(Model model) {
        // Note: É mais comum usar Page<T> e Pageable aqui, mas mantendo o .getContent() para fins de exemplo
        model.addAttribute("competencias", service.listar(org.springframework.data.domain.Pageable.unpaged()).getContent());
        return "competencias/lista";
    }

    // FORM PARA CRIAR (GET /competencias/novo)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("competencia", new CompetenciaDTO());
        return "competencias/editar";
    }

    // FORM PARA EDITAR (GET /competencias/editar/{id})
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // Certifique-se de que o service.buscarPorId() retorne um DTO
        model.addAttribute("competencia", service.buscarPorId(id));
        return "competencias/editar";
    }

    // SALVAR OU ATUALIZAR (POST /competencias/salvar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute CompetenciaDTO competencia) {
        if (competencia.getIdCompetencia() != null) {
            service.atualizar(competencia.getIdCompetencia(), competencia);
        } else {
            service.salvar(competencia);
        }
        return "redirect:/competencias/listar";
    }

    // DELETAR (GET /competencias/deletar/{id})
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        // 🛠️ FIX: Redireciona para a rota de listagem correta
        return "redirect:/competencias/listar";
    }
}