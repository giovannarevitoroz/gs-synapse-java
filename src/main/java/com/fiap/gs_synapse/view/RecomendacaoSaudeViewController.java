package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.RecomendacaoSaudeDTO;
import com.fiap.gs_synapse.service.RecomendacaoSaudeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/recomendacoes/saude")
public class RecomendacaoSaudeViewController {

    private final RecomendacaoSaudeService service;

    public RecomendacaoSaudeViewController(RecomendacaoSaudeService service) {
        this.service = service;
    }

    // LISTAR RECOMENDAÇÕES DE SAÚDE
    @GetMapping("/listar")
    public String listar(Model model, Locale locale) {
        model.addAttribute("recomendacoes", service.listarTodos(locale));
        model.addAttribute("recomendacaoSaudeDTO", new RecomendacaoSaudeDTO());
        return "recomendacao-saude";
    }

    // FORM NOVO
    @GetMapping("/novo")
    public String novo(Model model, Locale locale) {
        model.addAttribute("recomendacaoSaudeDTO", new RecomendacaoSaudeDTO());
        model.addAttribute("recomendacoes", service.listarTodos(locale));
        return "recomendacao-saude";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("recomendacaoSaudeDTO") RecomendacaoSaudeDTO dto, Locale locale) {
        service.criar(dto, locale);
        return "redirect:/recomendacoes/saude/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, Locale locale) {
        RecomendacaoSaudeDTO dto = service.buscarPorId(id, locale);
        model.addAttribute("recomendacaoSaudeDTO", dto);
        model.addAttribute("recomendacoes", service.listarTodos(locale));
        return "recomendacao-saude";
    }

    // ATUALIZAR
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute("recomendacaoSaudeDTO") RecomendacaoSaudeDTO dto, Locale locale) {
        service.atualizar(id, dto, locale);
        return "redirect:/recomendacoes/saude/listar";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Locale locale) {
        service.deletar(id, locale);
        return "redirect:/recomendacoes/saude/listar";
    }
}
