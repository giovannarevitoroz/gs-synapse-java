package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RecomendacaoDTO;
import com.fiap.gs_synapse.service.RecomendacaoService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recomendacoes")
public class RecomendacaoController {

    private final RecomendacaoService service;

    public RecomendacaoController(RecomendacaoService service) {
        this.service = service;
    }

    // LISTAR PAGINADO
    @GetMapping
    public ResponseEntity<Page<RecomendacaoDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // CRIAR
    @PostMapping
    public ResponseEntity<RecomendacaoDTO> criar(@Valid @RequestBody RecomendacaoDTO dto) {
        RecomendacaoDTO criado = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoDTO> atualizar(@PathVariable Long id,
                                                     @Valid @RequestBody RecomendacaoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
