package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.config.RabbitMQConfig;
import com.fiap.gs_synapse.dto.RegistroBemEstarDTO;
import com.fiap.gs_synapse.exception.BusinessException;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.messaging.EmailQueueProducer;
import com.fiap.gs_synapse.model.RegistroBemEstar;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.RegistroBemEstarRepository;
import com.fiap.gs_synapse.repository.UsuarioRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RegistroBemEstarService {

    private final RegistroBemEstarRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EmailQueueProducer emailProducer;

    public RegistroBemEstarService(
            RegistroBemEstarRepository repository,
            UsuarioRepository usuarioRepository,
            EmailQueueProducer emailProducer
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.emailProducer = emailProducer;
    }

    // LISTAR COM PAGINAÇÃO
    @Cacheable(value = "registrosBemEstar")
    public Page<RegistroBemEstarDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDTO);
    }

    // BUSCAR POR ID
    @Cacheable(value = "registroBemEstar", key = "#id")
    public RegistroBemEstarDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Registro de bem-estar não encontrado. ID: " + id));
    }

    // SALVAR
    @Transactional
    @CacheEvict(value = {"registrosBemEstar", "registroBemEstar"}, allEntries = true)
    public RegistroBemEstarDTO salvar(RegistroBemEstarDTO dto) {

        if (dto.getUsuarioId() == null) {
            throw new BusinessException("O ID do usuário é obrigatório");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado. ID: " + dto.getUsuarioId()
                ));

        RegistroBemEstar entity = toEntity(dto, usuario);
        RegistroBemEstar salvo = repository.save(entity);

        // Envia mensagem assíncrona via RabbitMQ
        emailProducer.enviarMensagem(
                "Novo registro bem-estar criado para o usuário " + usuario.getIdUsuario()
        );

        return toDTO(salvo);
    }

    // ATUALIZAR
    @Transactional
    @CacheEvict(value = {"registrosBemEstar", "registroBemEstar"}, allEntries = true)
    public RegistroBemEstarDTO atualizar(Long id, RegistroBemEstarDTO dto) {

        RegistroBemEstar existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Registro de bem-estar não encontrado. ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado. ID: " + dto.getUsuarioId()));

        existente.setDataRegistro(dto.getDataRegistro());
        existente.setHumorRegistro(dto.getHumorRegistro());
        existente.setHorasSono(dto.getHorasSono());
        existente.setHorasTrabalho(dto.getHorasTrabalho());
        existente.setNivelEnergia(dto.getNivelEnergia());
        existente.setNivelEstresse(dto.getNivelEstresse());
        existente.setObservacaoRegistro(dto.getObservacaoRegistro());
        existente.setUsuario(usuario);

        return toDTO(repository.save(existente));
    }

    // DELETAR
    @Transactional
    @CacheEvict(value = {"registrosBemEstar", "registroBemEstar"}, allEntries = true)
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Registro de bem-estar não encontrado para exclusão. ID: " + id);
        }
        repository.deleteById(id);
    }

    // ======================
    // CONVERSORES
    // ======================

    private RegistroBemEstarDTO toDTO(RegistroBemEstar r) {
        RegistroBemEstarDTO dto = new RegistroBemEstarDTO();
        dto.setIdRegistro(r.getIdRegistro());
        dto.setDataRegistro(r.getDataRegistro());
        dto.setHumorRegistro(r.getHumorRegistro());
        dto.setHorasSono(r.getHorasSono());
        dto.setHorasTrabalho(r.getHorasTrabalho());
        dto.setNivelEnergia(r.getNivelEnergia());
        dto.setNivelEstresse(r.getNivelEstresse());
        dto.setObservacaoRegistro(r.getObservacaoRegistro());
        dto.setUsuarioId(r.getUsuario().getIdUsuario());
        return dto;
    }

    private RegistroBemEstar toEntity(RegistroBemEstarDTO dto, Usuario usuario) {
        RegistroBemEstar r = new RegistroBemEstar();
        r.setIdRegistro(dto.getIdRegistro());
        r.setDataRegistro(dto.getDataRegistro());
        r.setHumorRegistro(dto.getHumorRegistro());
        r.setHorasSono(dto.getHorasSono());
        r.setHorasTrabalho(dto.getHorasTrabalho());
        r.setNivelEnergia(dto.getNivelEnergia());
        r.setNivelEstresse(dto.getNivelEstresse());
        r.setObservacaoRegistro(dto.getObservacaoRegistro());
        r.setUsuario(usuario);
        return r;
    }
}
