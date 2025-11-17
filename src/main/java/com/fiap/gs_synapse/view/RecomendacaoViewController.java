package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RecomendacaoDTO;
import com.fiap.gs_synapse.service.RecomendacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recomendacoes")
public class RecomendacaoViewController {

    private final RecomendacaoService service;

    public RecomendacaoViewController(RecomendacaoService service) {
        this.service = service;
    }

    // LISTAR RECOMENDAÇÕES
    @GetMapping("/listar") // FIX: Mapeado para /recomendacoes/listar
    public String listar(Model model) {
        model.addAttribute("recomendacoes", service.listarTodos());
        model.addAttribute("recomendacaoDTO", new RecomendacaoDTO());
        return "recomendacao";
    }

    // SALVAR NOVA RECOMENDAÇÃO
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute RecomendacaoDTO dto) {
        service.salvar(dto);
        return "redirect:/recomendacoes/listar"; // FIX: Redireciona para listar
    }

    // EDITAR RECOMENDAÇÃO
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        RecomendacaoDTO dto = service.buscarPorId(id);
        model.addAttribute("recomendacaoDTO", dto);
        return "editar-recomendacao";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute RecomendacaoDTO dto) {
        service.atualizar(id, dto);
        return "redirect:/recomendacoes/listar"; // FIX: Redireciona para listar
    }

    // DELETAR RECOMENDAÇÃO
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/recomendacoes/listar"; // FIX: Redireciona para listar
    }
}