package com.fiap.gs_synapse.view;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.model.Competencia;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.CompetenciaRepository;
import com.fiap.gs_synapse.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // LISTAR + FORM
    // 🛠️ AJUSTADO: A rota de listagem agora é explicitamente /usuarios/listar (GET /usuarios/listar)
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

    // SALVAR (POST /usuarios/salvar)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("usuarioDTO") UsuarioDTO dto, Locale locale) {
        if (dto.getIdUsuario() == null) {  // Criar
            usuarioService.criarUsuario(dto, locale);
        } else {                           // Atualizar
            usuarioService.atualizar(dto.getIdUsuario(), dto, locale);
        }
        return "redirect:/usuarios/listar"; // Redireciona para a nova rota de listagem
    }

    // EDITAR — CARREGA NO FORMULÁRIO (GET /usuarios/editar/{id})
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, Locale locale) {
        Usuario usuario = usuarioService.buscarPorId(id, locale);
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setSenhaUsuario(usuario.getSenhaUsuario());
        dto.setAreaAtual(usuario.getAreaAtual());
        dto.setAreaInteresse(usuario.getAreaInteresse());
        dto.setObjetivoCarreira(usuario.getObjetivoCarreira());
        dto.setNivelExperiencia(usuario.getNivelExperiencia());
        dto.setCompetenciasIds(
                usuario.getCompetencias().stream()
                        .map(Competencia::getIdCompetencia)
                        .collect(Collectors.toSet())
        );
        Page<Usuario> usuarios = usuarioService.listar(PageRequest.of(0, 10));
        model.addAttribute("usuarioDTO", dto);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("competencias", competenciaRepository.findAll());
        model.addAttribute("paginaAtual", 0);
        return "usuario";
    }

    // DELETAR (GET /usuarios/deletar/{id})
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Locale locale) {
        usuarioService.deletar(id, locale);
        return "redirect:/usuarios/listar"; // Redireciona para a nova rota de listagem
    }
}