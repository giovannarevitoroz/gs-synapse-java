package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.model.Competencia;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.CompetenciaRepository;
import com.fiap.gs_synapse.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;
    private final CompetenciaRepository competenciaRepository;

    public UsuarioViewController(UsuarioService usuarioService,
                                 CompetenciaRepository competenciaRepository) {
        this.usuarioService = usuarioService;
        this.competenciaRepository = competenciaRepository;
    }

    // LISTAR USUÁRIOS COM FORMULÁRIO
    @GetMapping("/listar")
    public String listar(Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size) {

        Page<Usuario> usuarios = usuarioService.listar(PageRequest.of(page, size));

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        model.addAttribute("competencias", competenciaRepository.findAll());
        model.addAttribute("paginaAtual", page);

        return "usuario";
    }

    // SALVAR NOVO USUÁRIO OU ATUALIZAR EXISTENTE
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO dto,
                         BindingResult result,
                         Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("competencias", competenciaRepository.findAll());
            model.addAttribute("usuarios", usuarioService.listar(PageRequest.of(page, size)));
            model.addAttribute("paginaAtual", page);
            return "usuario";
        }

        if (dto.getIdUsuario() == null) {
            usuarioService.criarUsuario(dto, locale);
        } else {
            usuarioService.atualizar(dto.getIdUsuario(), dto, locale);
        }

        return "redirect:/usuarios/listar";
    }

    // EDITAR USUÁRIO
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         Locale locale) {

        Usuario usuario = usuarioService.buscarPorId(id, locale);
        UsuarioDTO dto = mapParaDTO(usuario);

        Page<Usuario> usuarios = usuarioService.listar(PageRequest.of(page, size));

        model.addAttribute("usuarioDTO", dto);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("competencias", competenciaRepository.findAll());
        model.addAttribute("paginaAtual", page);

        return "usuario";
    }

    // DELETAR USUÁRIO
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Locale locale) {
        usuarioService.deletar(id, locale);
        return "redirect:/usuarios/listar";
    }

    // MÉTODO PRIVADO PARA MAPPING DE DTO
    private UsuarioDTO mapParaDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setAreaAtual(usuario.getAreaAtual());
        dto.setAreaInteresse(usuario.getAreaInteresse());
        dto.setObjetivoCarreira(usuario.getObjetivoCarreira());
        dto.setNivelExperiencia(usuario.getNivelExperiencia());
        dto.setCompetenciasIds(
                usuario.getCompetencias().stream()
                        .map(Competencia::getIdCompetencia)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
