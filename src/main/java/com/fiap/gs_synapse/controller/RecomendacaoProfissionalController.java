package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RecomendacaoProfissionalDTO;
import com.fiap.gs_synapse.service.RecomendacaoProfissionalService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendacoes-profissionais")
public class RecomendacaoProfissionalController {

    private final RecomendacaoProfissionalService service;

    public RecomendacaoProfissionalController(RecomendacaoProfissionalService service) {
        this.service = service;
    }

    // --------------------------------------
    // LISTAR TODOS
    // --------------------------------------
    @GetMapping
    public ResponseEntity<List<RecomendacaoProfissionalDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // --------------------------------------
    // LISTAR COM PAGINAÇÃO
    // /api/recomendacoes-profissionais/paginado?page=0&size=10
    // --------------------------------------
    @GetMapping("/paginado")
    public ResponseEntity<Page<RecomendacaoProfissionalDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    // --------------------------------------
    // BUSCAR POR ID
    // --------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoProfissionalDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // --------------------------------------
    // CRIAR NOVO
    // --------------------------------------
    @PostMapping
    public ResponseEntity<RecomendacaoProfissionalDTO> salvar(@Valid @RequestBody RecomendacaoProfissionalDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
    }

    // --------------------------------------
    // ATUALIZAR
    // --------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacaoProfissionalDTO> atualizar(@PathVariable Long id,
                                                                 @Valid @RequestBody RecomendacaoProfissionalDTO dto) {
        dto.setIdRecomendacao(id);
        return ResponseEntity.ok(service.salvar(dto));
    }

    // --------------------------------------
    // DELETAR
    // --------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
