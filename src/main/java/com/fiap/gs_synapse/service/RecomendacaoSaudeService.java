package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.dto.RecomendacaoSaudeDTO;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.model.Recomendacao;
import com.fiap.gs_synapse.model.RecomendacaoSaude;
import com.fiap.gs_synapse.repository.RecomendacaoRepository;
import com.fiap.gs_synapse.repository.RecomendacaoSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Locale;

@Service
public class RecomendacaoSaudeService {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private RecomendacaoSaudeRepository recomendacaoSaudeRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "recomendacaoSaudeQueue";

    @Transactional
    @CacheEvict(value = "recomendacoesSaude", allEntries = true)
    public RecomendacaoSaudeDTO criar(RecomendacaoSaudeDTO dto, Locale locale) {

        Recomendacao recomendacao = recomendacaoRepository.findById(dto.getIdRecomendacao())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("recomendacao.nao.encontrada", null, locale)
                ));

        RecomendacaoSaude saude = new RecomendacaoSaude();
        saude.setIdRecomendacao(dto.getIdRecomendacao());
        saude.setTipoSaude(dto.getTipoSaude());
        saude.setNivelAlerta(dto.getNivelAlerta());
        saude.setMensagemSaude(dto.getMensagemSaude());
        saude.setRecomendacao(recomendacao);

        recomendacaoSaudeRepository.save(saude);

        // Envio assíncrono para fila
        rabbitTemplate.convertAndSend(QUEUE_NAME, "Nova recomendação de saúde criada: " + saude.getIdRecomendacao());

        return dto;
    }

    @Cacheable(value = "recomendacoesSaude")
    public RecomendacaoSaudeDTO buscarPorId(Long id, Locale locale) {

        RecomendacaoSaude saude = recomendacaoSaudeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("recomendacao.saude.nao.encontrada", null, locale)
                ));

        RecomendacaoSaudeDTO dto = new RecomendacaoSaudeDTO();
        dto.setIdRecomendacao(saude.getIdRecomendacao());
        dto.setTipoSaude(saude.getTipoSaude());
        dto.setNivelAlerta(saude.getNivelAlerta());
        dto.setMensagemSaude(saude.getMensagemSaude());

        return dto;
    }

    @Transactional
    @CacheEvict(value = "recomendacoesSaude", allEntries = true)
    public RecomendacaoSaudeDTO atualizar(Long id, RecomendacaoSaudeDTO dto, Locale locale) {

        RecomendacaoSaude saude = recomendacaoSaudeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("recomendacao.saude.nao.encontrada", null, locale)
                ));

        saude.setTipoSaude(dto.getTipoSaude());
        saude.setNivelAlerta(dto.getNivelAlerta());
        saude.setMensagemSaude(dto.getMensagemSaude());

        recomendacaoSaudeRepository.save(saude);

        return dto;
    }

    @Transactional
    @CacheEvict(value = "recomendacoesSaude", allEntries = true)
    public void deletar(Long id, Locale locale) {

        if (!recomendacaoSaudeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("recomendacao.saude.nao.encontrada", null, locale)
            );
        }

        recomendacaoSaudeRepository.deleteById(id);
    }
}
