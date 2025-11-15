package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.config.RabbitMQConfig;
import com.fiap.gs_synapse.dto.RecomendacaoDTO;
import com.fiap.gs_synapse.exception.BusinessException;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.model.Recomendacao;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.RecomendacaoRepository;
import com.fiap.gs_synapse.repository.UsuarioRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecomendacaoService {

    private final RecomendacaoRepository recomendacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RabbitTemplate rabbitTemplate;

    public RecomendacaoService(RecomendacaoRepository recomendacaoRepository,
                               UsuarioRepository usuarioRepository,
                               RabbitTemplate rabbitTemplate) {
        this.recomendacaoRepository = recomendacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    // LISTAR PAGINADO + CACHE
    @Cacheable(value = "recomendacoes", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<RecomendacaoDTO> listar(Pageable pageable) {
        return recomendacaoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    // BUSCAR POR ID + CACHE
    @Cacheable(value = "recomendacao", key = "#id")
    public RecomendacaoDTO buscarPorId(Long id) {
        return recomendacaoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("recomendacao.nao.encontrada"));
    }

    // SALVAR
    @Transactional
    @CacheEvict(value = {"recomendacoes", "recomendacao"}, allEntries = true)
    public RecomendacaoDTO salvar(RecomendacaoDTO dto) {

        if (dto.getTituloRecomendacao().isBlank()) {
            throw new BusinessException("titulo.obrigatorio");
        }

        Usuario usuario = null;

        if (dto.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("usuario.nao.encontrado"));
        }

        Recomendacao nova = toEntity(dto, usuario);
        Recomendacao salva = recomendacaoRepository.save(nova);

        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.QUEUE_EMAIL,
                    "Recomendação criada: " + salva.getTituloRecomendacao()
            );
        } catch (Exception ex) {
            System.err.println("Falha ao enviar mensagem: " + ex.getMessage());
        }

        return toDTO(salva);
    }

    // ATUALIZAR
    @Transactional
    @CacheEvict(value = {"recomendacoes", "recomendacao"}, allEntries = true)
    public RecomendacaoDTO atualizar(Long id, RecomendacaoDTO dto) {

        Recomendacao existente = recomendacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("recomendacao.nao.encontrada"));

        if (dto.getTituloRecomendacao() != null)
            existente.setTituloRecomendacao(dto.getTituloRecomendacao());

        if (dto.getDescricaoRecomendacao() != null)
            existente.setDescricaoRecomendacao(dto.getDescricaoRecomendacao());

        if (dto.getPromptUsado() != null)
            existente.setPromptUsado(dto.getPromptUsado());

        if (dto.getDataRecomendacao() != null)
            existente.setDataRecomendacao(dto.getDataRecomendacao());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("usuario.nao.encontrado"));
            existente.setUsuario(usuario);
        }

        Recomendacao atualizado = recomendacaoRepository.save(existente);
        return toDTO(atualizado);
    }

    // DELETAR
    @Transactional
    @CacheEvict(value = {"recomendacoes", "recomendacao"}, allEntries = true)
    public void deletar(Long id) {
        if (!recomendacaoRepository.existsById(id))
            throw new ResourceNotFoundException("recomendacao.nao.encontrada");

        recomendacaoRepository.deleteById(id);
    }

    // CONVERTERS
    private RecomendacaoDTO toDTO(Recomendacao r) {
        RecomendacaoDTO dto = new RecomendacaoDTO();
        dto.setIdRecomendacao(r.getIdRecomendacao());
        dto.setDataRecomendacao(r.getDataRecomendacao());
        dto.setDescricaoRecomendacao(r.getDescricaoRecomendacao());
        dto.setPromptUsado(r.getPromptUsado());
        dto.setTituloRecomendacao(r.getTituloRecomendacao());
        if (r.getUsuario() != null)
            dto.setUsuarioId(r.getUsuario().getIdUsuario());
        return dto;
    }

    private Recomendacao toEntity(RecomendacaoDTO dto, Usuario usuario) {
        Recomendacao r = new Recomendacao();
        r.setIdRecomendacao(dto.getIdRecomendacao());
        r.setDataRecomendacao(dto.getDataRecomendacao());
        r.setDescricaoRecomendacao(dto.getDescricaoRecomendacao());
        r.setPromptUsado(dto.getPromptUsado());
        r.setTituloRecomendacao(dto.getTituloRecomendacao());
        r.setUsuario(usuario);
        return r;
    }
}
