package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.dto.RecomendacaoProfissionalDTO;
import com.fiap.gs_synapse.exception.BusinessException;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.model.Recomendacao;
import com.fiap.gs_synapse.model.RecomendacaoProfissional;
import com.fiap.gs_synapse.repository.RecomendacaoProfissionalRepository;
import com.fiap.gs_synapse.repository.RecomendacaoRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacaoProfissionalService {

    private final RecomendacaoProfissionalRepository repository;
    private final RecomendacaoRepository recomendacaoRepository;

    public RecomendacaoProfissionalService(RecomendacaoProfissionalRepository repository,
                                           RecomendacaoRepository recomendacaoRepository) {
        this.repository = repository;
        this.recomendacaoRepository = recomendacaoRepository;
    }

    // ------------------------
    // LISTAR TODOS (PAGINADO + CACHE)
    // ------------------------
    @Cacheable(value = "recomendacoesProfissionais")
    public Page<RecomendacaoProfissionalDTO> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO);
    }

    // ------------------------
    // LISTAR TODOS (SEM PAGINAR)
    // ------------------------
    public List<RecomendacaoProfissionalDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ------------------------
    // BUSCAR POR ID + CACHE
    // ------------------------
    @Cacheable(value = "recomendacaoProfissional", key = "#id")
    public RecomendacaoProfissionalDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recomendação Profissional não encontrada. ID: " + id));
    }

    // ------------------------
    // SALVAR / ATUALIZAR (LIMPA O CACHE AO ALTERAR)
    // ------------------------
    @CacheEvict(value = {"recomendacoesProfissionais", "recomendacaoProfissional"}, allEntries = true)
    public RecomendacaoProfissionalDTO salvar(RecomendacaoProfissionalDTO dto) {

        if (dto.getCategoriaRecomendacao() == null || dto.getCategoriaRecomendacao().isBlank()) {
            throw new BusinessException("Categoria da recomendação é obrigatória");
        }

        if (dto.getAreaRecomendacao() == null || dto.getAreaRecomendacao().isBlank()) {
            throw new BusinessException("Área da recomendação é obrigatória");
        }

        if (dto.getFonteRecomendacao() == null || dto.getFonteRecomendacao().isBlank()) {
            throw new BusinessException("Fonte da recomendação é obrigatória");
        }

        Recomendacao recomendacao = recomendacaoRepository.findById(dto.getIdRecomendacao())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recomendação não encontrada para ID: " + dto.getIdRecomendacao()));

        RecomendacaoProfissional entity = toEntity(dto, recomendacao);

        return toDTO(repository.save(entity));
    }

    // ------------------------
    // DELETAR (LIMPA CACHE)
    // ------------------------
    @CacheEvict(value = {"recomendacoesProfissionais", "recomendacaoProfissional"}, allEntries = true)
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recomendação Profissional não encontrada para exclusão. ID: " + id);
        }
        repository.deleteById(id);
    }

    // ========================
    // CONVERSÕES DTO ↔ ENTITY
    // ========================
    private RecomendacaoProfissionalDTO toDTO(RecomendacaoProfissional rp) {
        RecomendacaoProfissionalDTO dto = new RecomendacaoProfissionalDTO();
        dto.setIdRecomendacao(rp.getIdRecomendacao());
        dto.setCategoriaRecomendacao(rp.getCategoriaRecomendacao());
        dto.setAreaRecomendacao(rp.getAreaRecomendacao());
        dto.setFonteRecomendacao(rp.getFonteRecomendacao());
        return dto;
    }

    private RecomendacaoProfissional toEntity(RecomendacaoProfissionalDTO dto, Recomendacao recomendacao) {
        RecomendacaoProfissional rp = new RecomendacaoProfissional();
        rp.setIdRecomendacao(dto.getIdRecomendacao());
        rp.setCategoriaRecomendacao(dto.getCategoriaRecomendacao());
        rp.setAreaRecomendacao(dto.getAreaRecomendacao());
        rp.setFonteRecomendacao(dto.getFonteRecomendacao());
        rp.setRecomendacao(recomendacao);
        return rp;
    }
}
