package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.UsuarioRepository;
import com.fiap.gs_synapse.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller; // 💡 Alterado de @RestController para @Controller
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // Usamos @Controller para permitir o funcionamento dos redirecionamentos (String) do Thymeleaf
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ================= ADMIN (Endpoints REST) =================
    // Estes métodos retornam ResponseEntity e não precisam de @ResponseBody

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping // Para criar via API (requer JSON)
    public ResponseEntity<Usuario> criar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = service.criarUsuario(dto, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/api-list")
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id, LocaleContextHolder.getLocale()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto, LocaleContextHolder.getLocale()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id, LocaleContextHolder.getLocale());
        return ResponseEntity.noContent().build();
    }

    // ================= REGISTRO PÚBLICO (Endpoint MVC/Thymeleaf) =================
    // O retorno String é interpretado corretamente como um redirecionamento, pois a classe é um @Controller.
    @PostMapping("/registrar")
    public String registrarNovoUsuario(@Valid UsuarioDTO usuarioDTO,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Se houver erros de validação (além do 'role', que já foi resolvido), retorna a mensagem
            redirectAttributes.addFlashAttribute("usuarioDTO", usuarioDTO);
            redirectAttributes.addFlashAttribute("errorMessage", "Preencha todos os campos corretamente.");
            return "redirect:/login";
        }

        if (usuarioRepository.findByNomeUsuario(usuarioDTO.getNomeUsuario()).isPresent()) {
            redirectAttributes.addFlashAttribute("usuarioDTO", usuarioDTO);
            redirectAttributes.addFlashAttribute("errorMessage", "Nome de usuário já está em uso.");
            return "redirect:/login";
        }

        // Converte DTO para Entity
        Usuario novoUsuario = convertDtoToEntity(usuarioDTO);

        // Define ROLE padrão (RESOLVE O ERRO ORIGINAL DE VALIDAÇÃO: 'role must not be blank')
        novoUsuario.setRole("ROLE_USER");

        // Criptografa senha
        novoUsuario.setSenhaUsuario(passwordEncoder.encode(usuarioDTO.getSenhaUsuario())); // Garante que usa a senha do DTO

        // Salva no banco
        usuarioRepository.save(novoUsuario);

        redirectAttributes.addFlashAttribute("successMessage", "Conta criada com sucesso! Faça login.");
        return "redirect:/login";
    }

    // ================= MÉTODO AUXILIAR =================
    private Usuario convertDtoToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        // Não copia a senha aqui, pois ela será criptografada logo em seguida no registrarNovoUsuario
        usuario.setAreaAtual(dto.getAreaAtual());
        usuario.setAreaInteresse(dto.getAreaInteresse());
        usuario.setObjetivoCarreira(dto.getObjetivoCarreira());
        usuario.setNivelExperiencia(dto.getNivelExperiencia());
        return usuario;
    }
}