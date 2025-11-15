package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.dto.CompetenciaDTO;
import com.fiap.gs_synapse.exception.BusinessException;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.model.Competencia;
import com.fiap.gs_synapse.repository.CompetenciaRepository;
import com.fiap.gs_synapse.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CompetenciaService {

    private final CompetenciaRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public CompetenciaService(CompetenciaRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    // LISTAR COM PAGINAÇÃO (cache para páginas)
    @Cacheable(value = "competencias", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<CompetenciaDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO);
    }

    // BUSCAR POR ID (cache individual)
    @Cacheable(value = "competencia", key = "#id")
    public CompetenciaDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada. ID: " + id));
    }

    // SALVAR
    @Transactional
    @CacheEvict(value = {"competencias"}, allEntries = true)
    public CompetenciaDTO salvar(CompetenciaDTO dto) {

        // validações de negócio adicionais (exemplo)
        if (dto.getNomeCompetencia() == null || dto.getNomeCompetencia().isBlank()) {
            throw new BusinessException("Nome da competência é obrigatório");
        }

        Competencia entity = toEntity(dto);
        Competencia salvo = repository.save(entity);

        // envio de mensagem assíncrona (ex: notificação/email)
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_EMAIL,
                "Nova competência criada: id=" + salvo.getIdCompetencia() + " nome=" + salvo.getNomeCompetencia());

        return toDTO(salvo);
    }

    // ATUALIZAR
    @Transactional
    @CacheEvict(value = {"competencias", "competencia"}, allEntries = true)
    public CompetenciaDTO atualizar(Long id, CompetenciaDTO dto) {
        Competencia existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada. ID: " + id));

        existente.setNomeCompetencia(dto.getNomeCompetencia());
        existente.setCategoriaCompetencia(dto.getCategoriaCompetencia());
        existente.setDescricaoCompetencia(dto.getDescricaoCompetencia());

        Competencia atualizado = repository.save(existente);
        return toDTO(atualizado);
    }

    // DELETAR
    @Transactional
    @CacheEvict(value = {"competencias", "competencia"}, allEntries = true)
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Competência não encontrada para exclusão. ID: " + id);
        }
        repository.deleteById(id);
    }

    // ========================
    // conversões
    // ========================
    private CompetenciaDTO toDTO(Competencia c) {
        CompetenciaDTO dto = new CompetenciaDTO();
        dto.setIdCompetencia(c.getIdCompetencia());
        dto.setNomeCompetencia(c.getNomeCompetencia());
        dto.setCategoriaCompetencia(c.getCategoriaCompetencia());
        dto.setDescricaoCompetencia(c.getDescricaoCompetencia());
        return dto;
    }

    private Competencia toEntity(CompetenciaDTO dto) {
        Competencia c = new Competencia();
        if (dto.getIdCompetencia() != null) {
            c.setIdCompetencia(dto.getIdCompetencia());
        }
        c.setNomeCompetencia(dto.getNomeCompetencia());
        c.setCategoriaCompetencia(dto.getCategoriaCompetencia());
        c.setDescricaoCompetencia(dto.getDescricaoCompetencia());
        return c;
    }
}
