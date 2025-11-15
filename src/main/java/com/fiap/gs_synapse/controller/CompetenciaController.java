package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.CompetenciaDTO;
import com.fiap.gs_synapse.service.CompetenciaService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competencias")
public class CompetenciaController {

    private final CompetenciaService service;

    public CompetenciaController(CompetenciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CompetenciaDTO>> listar(Pageable pageable) {
        Page<CompetenciaDTO> page = service.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> buscar(@PathVariable Long id) {
        CompetenciaDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CompetenciaDTO> criar(@Valid @RequestBody CompetenciaDTO dto) {
        CompetenciaDTO criado = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CompetenciaDTO dto) {
        CompetenciaDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
