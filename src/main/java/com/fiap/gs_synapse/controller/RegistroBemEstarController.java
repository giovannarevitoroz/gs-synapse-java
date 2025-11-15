package com.fiap.gs_synapse.controller;

import com.fiap.gs_synapse.dto.RegistroBemEstarDTO;
import com.fiap.gs_synapse.service.RegistroBemEstarService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros-bem-estar")
public class RegistroBemEstarController {

    private final RegistroBemEstarService service;

    public RegistroBemEstarController(RegistroBemEstarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<RegistroBemEstarDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroBemEstarDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RegistroBemEstarDTO> criar(@Valid @RequestBody RegistroBemEstarDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroBemEstarDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RegistroBemEstarDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
