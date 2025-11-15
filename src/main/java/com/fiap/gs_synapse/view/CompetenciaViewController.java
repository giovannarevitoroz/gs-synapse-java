package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.CompetenciaDTO;
import com.fiap.gs_synapse.service.CompetenciaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competencias")
public class CompetenciaViewController {

    private final CompetenciaService service;

    public CompetenciaViewController(CompetenciaService service) {
        this.service = service;
    }

    // LISTAR COMPETÊNCIAS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competencias", service.listar(org.springframework.data.domain.Pageable.unpaged()).getContent());
        return "competencias/lista";
    }

    // FORM PARA CRIAR
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("competencia", new CompetenciaDTO());
        return "competencias/editar";
    }

    // FORM PARA EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("competencia", service.buscarPorId(id));
        return "competencias/editar";
    }

    // SALVAR OU ATUALIZAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute CompetenciaDTO competencia) {
        if (competencia.getIdCompetencia() != null) {
            service.atualizar(competencia.getIdCompetencia(), competencia);
        } else {
            service.salvar(competencia);
        }
        return "redirect:/competencias";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/competencias";
    }
}
