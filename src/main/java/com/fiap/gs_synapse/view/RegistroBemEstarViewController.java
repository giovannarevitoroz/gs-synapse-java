package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RegistroBemEstarDTO;
import com.fiap.gs_synapse.service.RegistroBemEstarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bemestar")
public class RegistroBemEstarViewController {

    private final RegistroBemEstarService service;

    public RegistroBemEstarViewController(RegistroBemEstarService service) {
        this.service = service;
    }

    // LISTAR + FORM
    @GetMapping
    public String listar(Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size) {

        Page<RegistroBemEstarDTO> registros = service.listar(PageRequest.of(page, size));

        model.addAttribute("registros", registros);
        model.addAttribute("registroDTO", new RegistroBemEstarDTO());
        model.addAttribute("paginaAtual", page);

        return "registro-bem-estar";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("registroDTO") RegistroBemEstarDTO dto) {
        if (dto.getIdRegistro() == null) {
            service.salvar(dto);
        } else {
            service.atualizar(dto.getIdRegistro(), dto);
        }
        return "redirect:/bemestar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        RegistroBemEstarDTO dto = service.buscarPorId(id);
        Page<RegistroBemEstarDTO> registros = service.listar(PageRequest.of(0, 10));

        model.addAttribute("registroDTO", dto);
        model.addAttribute("registros", registros);
        model.addAttribute("paginaAtual", 0);

        return "registro-bem-estar";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/bemestar";
    }
}
