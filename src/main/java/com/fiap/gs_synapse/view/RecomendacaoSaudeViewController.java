package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.RecomendacaoDTO;
import com.fiap.gs_synapse.service.RecomendacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recomendacoes/saude")
public class RecomendacaoSaudeViewController {

    private final RecomendacaoService service;

    public RecomendacaoSaudeViewController(RecomendacaoService service) {
        this.service = service;
    }

    // LISTAR RECOMENDAÇÕES DE SAÚDE
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("recomendacoes", service.listarTodos());
        model.addAttribute("recomendacaoSaudeDTO", new RecomendacaoDTO());
        return "recomendacao-saude";
    }

    // FORM NOVO
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("recomendacaoSaudeDTO", new RecomendacaoDTO());
        model.addAttribute("recomendacoes", service.listarTodos());
        return "recomendacao-saude";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("recomendacaoSaudeDTO") RecomendacaoDTO dto) {
        service.salvar(dto);
        return "redirect:/recomendacoes/saude/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        RecomendacaoDTO dto = service.buscarPorId(id);
        model.addAttribute("recomendacaoSaudeDTO", dto);
        model.addAttribute("recomendacoes", service.listarTodos());
        return "recomendacao-saude";
    }

    // ATUALIZAR
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute("recomendacaoSaudeDTO") RecomendacaoDTO dto) {
        service.atualizar(id, dto);
        return "redirect:/recomendacoes/saude/listar";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/recomendacoes/saude/listar";
    }
}
