package com.fiap.gs_synapse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class RecomendacaoDTO {

    private Long idRecomendacao;

    @NotNull
    private LocalDate dataRecomendacao;

    @NotBlank
    private String descricaoRecomendacao;

    @NotBlank
    private String promptUsado;

    @NotBlank
    private String tituloRecomendacao;

    private Long usuarioId;

    // getters e setters

    public Long getIdRecomendacao() {
        return idRecomendacao;
    }

    public void setIdRecomendacao(Long idRecomendacao) {
        this.idRecomendacao = idRecomendacao;
    }

    public @NotNull LocalDate getDataRecomendacao() {
        return dataRecomendacao;
    }

    public void setDataRecomendacao(@NotNull LocalDate dataRecomendacao) {
        this.dataRecomendacao = dataRecomendacao;
    }

    public @NotBlank String getPromptUsado() {
        return promptUsado;
    }

    public void setPromptUsado(@NotBlank String promptUsado) {
        this.promptUsado = promptUsado;
    }

    public @NotBlank String getDescricaoRecomendacao() {
        return descricaoRecomendacao;
    }

    public void setDescricaoRecomendacao(@NotBlank String descricaoRecomendacao) {
        this.descricaoRecomendacao = descricaoRecomendacao;
    }

    public @NotBlank String getTituloRecomendacao() {
        return tituloRecomendacao;
    }

    public void setTituloRecomendacao(@NotBlank String tituloRecomendacao) {
        this.tituloRecomendacao = tituloRecomendacao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
