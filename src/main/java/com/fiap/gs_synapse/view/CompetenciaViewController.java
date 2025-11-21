package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.CompetenciaDTO;
import com.fiap.gs_synapse.service.CompetenciaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    // LISTAR COMPETÊNCIAS COM PAGINAÇÃO
    @GetMapping({"/listar", "/"})
    public String listar(Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size) {

        Page<CompetenciaDTO> competenciasPage = service.listar(PageRequest.of(page, size));

        model.addAttribute("competencias", competenciasPage.getContent());
        model.addAttribute("paginaAtual", competenciasPage.getNumber());
        model.addAttribute("totalPaginas", competenciasPage.getTotalPages());
        model.addAttribute("competencia", new CompetenciaDTO()); // formulário vazio para criação

        return "competencias"; // mesmo template para listar, criar e editar
    }

    // FORM PARA CRIAR (GET /competencias/novo)
    @GetMapping("/novo")
    public String novo(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {

        Page<CompetenciaDTO> competenciasPage = service.listar(PageRequest.of(page, size));

        model.addAttribute("competencias", competenciasPage.getContent());
        model.addAttribute("paginaAtual", competenciasPage.getNumber());
        model.addAttribute("totalPaginas", competenciasPage.getTotalPages());
        model.addAttribute("competencia", new CompetenciaDTO()); // formulário vazio

        return "competencias";
    }

    // FORM PARA EDITAR (GET /competencias/editar/{id})
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size) {

        CompetenciaDTO dto = service.buscarPorId(id);
        Page<CompetenciaDTO> competenciasPage = service.listar(PageRequest.of(page, size));

        model.addAttribute("competencias", competenciasPage.getContent());
        model.addAttribute("paginaAtual", competenciasPage.getNumber());
        model.addAttribute("totalPaginas", competenciasPage.getTotalPages());
        model.addAttribute("competencia", dto); // formulário preenchido para edição

        return "competencias";
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
        return "redirect:/competencias/listar";
    }
}
