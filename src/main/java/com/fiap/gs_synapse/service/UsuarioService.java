package com.fiap.gs_synapse.service;

import com.fiap.gs_synapse.dto.UsuarioDTO;
import com.fiap.gs_synapse.exception.ResourceNotFoundException;
import com.fiap.gs_synapse.model.Competencia;
import com.fiap.gs_synapse.model.Usuario;
import com.fiap.gs_synapse.repository.CompetenciaRepository;
import com.fiap.gs_synapse.repository.UsuarioRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageSource messageSource;

    @CacheEvict(value = "usuarios", allEntries = true)
    public Usuario criarUsuario(UsuarioDTO dto, Locale locale) {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());
        usuario.setAreaAtual(dto.getAreaAtual());
        usuario.setAreaInteresse(dto.getAreaInteresse());
        usuario.setObjetivoCarreira(dto.getObjetivoCarreira());
        usuario.setNivelExperiencia(dto.getNivelExperiencia());

        Set<Competencia> competencias = new HashSet<>();
        if (dto.getCompetenciasIds() != null) {
            dto.getCompetenciasIds().forEach(id ->
                    competenciaRepository.findById(id).ifPresent(competencias::add)
            );
        }
        usuario.setCompetencias(competencias);

        Usuario salvo = usuarioRepository.save(usuario);

        // Envia mensagem assíncrona RabbitMQ
        rabbitTemplate.convertAndSend("emailQueue",
                "Novo usuário criado: " + salvo.getNomeUsuario());

        return salvo;
    }

    @Cacheable(value = "usuarios")
    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Cacheable(value = "usuario", key = "#id")
    public Usuario buscarPorId(Long id, Locale locale) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                messageSource.getMessage("usuario.nao.encontrado", null, locale)
                        ));
    }

    @CacheEvict(value = {"usuario", "usuarios"}, allEntries = true)
    public Usuario atualizar(Long id, UsuarioDTO dto, Locale locale) {

        Usuario usuario = buscarPorId(id, locale);

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());
        usuario.setAreaAtual(dto.getAreaAtual());
        usuario.setAreaInteresse(dto.getAreaInteresse());
        usuario.setObjetivoCarreira(dto.getObjetivoCarreira());
        usuario.setNivelExperiencia(dto.getNivelExperiencia());

        Set<Competencia> novas = new HashSet<>();
        if (dto.getCompetenciasIds() != null) {
            dto.getCompetenciasIds().forEach(cid ->
                    competenciaRepository.findById(cid).ifPresent(novas::add));
        }
        usuario.setCompetencias(novas);

        // Mensagem assíncrona
        rabbitTemplate.convertAndSend("emailQueue",
                "Usuário atualizado: " + usuario.getNomeUsuario());

        return usuarioRepository.save(usuario);
    }

    @CacheEvict(value = {"usuario", "usuarios"}, allEntries = true)
    public void deletar(Long id, Locale locale) {

        Usuario usuario = buscarPorId(id, locale);

        usuarioRepository.delete(usuario);

        // Mensagem assíncrona no Rabbit
        rabbitTemplate.convertAndSend("emailQueue",
                "Usuário deletado: " + usuario.getNomeUsuario());
    }
}
