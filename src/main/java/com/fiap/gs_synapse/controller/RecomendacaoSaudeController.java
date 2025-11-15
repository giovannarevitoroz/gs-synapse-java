package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RecomendacaoSaudeDTO;
import com.fiap.gs_synapse.service.RecomendacaoSaudeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recomendacoes/saude")
public class RecomendacaoSaudeController {

    @Autowired
    private RecomendacaoSaudeService service;

    @Autowired
    private com.fiap.gs_synapse.repository.RecomendacaoSaudeRepository repository;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RecomendacaoSaudeDTO> criar(
            @Valid @RequestBody RecomendacaoSaudeDTO dto) {

        var locale = LocaleContextHolder.getLocale();
        var criado = service.criar(dto, locale);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoSaudeDTO> buscar(@PathVariable Long id) {
        var locale = LocaleContextHolder.getLocale();
        return ResponseEntity.ok(service.buscarPorId(id, locale));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoSaudeDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RecomendacaoSaudeDTO dto) {

        var locale = LocaleContextHolder.getLocale();
        return ResponseEntity.ok(service.atualizar(id, dto, locale));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        var locale = LocaleContextHolder.getLocale();
        service.deletar(id, locale);
        return ResponseEntity.noContent().build();
    }

    // Paginação
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public Page<RecomendacaoSaudeDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(saude -> {
                    RecomendacaoSaudeDTO dto = new RecomendacaoSaudeDTO();
                    dto.setIdRecomendacao(saude.getIdRecomendacao());
                    dto.setTipoSaude(saude.getTipoSaude());
                    dto.setMensagemSaude(saude.getMensagemSaude());
                    dto.setNivelAlerta(saude.getNivelAlerta());
                    return dto;
                });
    }
}
