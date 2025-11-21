package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.RecomendacaoProfissionalDTO;
import com.fiap.gs_synapse.service.RecomendacaoProfissionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recomendacoes/profissionais")
public class RecomendacaoProfissionalViewController {

    private final RecomendacaoProfissionalService service;

    public RecomendacaoProfissionalViewController(RecomendacaoProfissionalService service) {
        this.service = service;
    }

    // LISTAR TODAS
    @GetMapping("/listar")
    public String listarTodas(Model model) {
        List<RecomendacaoProfissionalDTO> recomendacoes = service.listarTodos();
        model.addAttribute("recomendacoes", recomendacoes);
        model.addAttribute("recomendacaoProfissionalDTO", new RecomendacaoProfissionalDTO());
        return "recomendacao-profissional";
    }

    // SALVAR NOVA OU ATUALIZAR EXISTENTE
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("recomendacaoProfissionalDTO") RecomendacaoProfissionalDTO dto) {
        service.salvar(dto);
        return "redirect:/recomendacoes/profissionais/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        RecomendacaoProfissionalDTO dto = service.buscarPorId(id);
        List<RecomendacaoProfissionalDTO> recomendacoes = service.listarTodos();
        model.addAttribute("recomendacaoProfissionalDTO", dto);
        model.addAttribute("recomendacoes", recomendacoes);
        return "recomendacao-profissional";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/recomendacoes/profissionais/listar";
    }
}
