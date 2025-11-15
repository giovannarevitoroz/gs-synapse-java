package com.fiap.gs_synapse.dto;

import jakarta.validation.constraints.NotBlank;

public class RecomendacaoProfissionalDTO {

    private Long idRecomendacao;

    @NotBlank
    private String categoriaRecomendacao;

    @NotBlank
    private String areaRecomendacao;

    @NotBlank
    private String fonteRecomendacao;

    // getters e setters

    public Long getIdRecomendacao() {
        return idRecomendacao;
    }

    public void setIdRecomendacao(Long idRecomendacao) {
        this.idRecomendacao = idRecomendacao;
    }

    public @NotBlank String getAreaRecomendacao() {
        return areaRecomendacao;
    }

    public void setAreaRecomendacao(@NotBlank String areaRecomendacao) {
        this.areaRecomendacao = areaRecomendacao;
    }

    public @NotBlank String getCategoriaRecomendacao() {
        return categoriaRecomendacao;
    }

    public void setCategoriaRecomendacao(@NotBlank String categoriaRecomendacao) {
        this.categoriaRecomendacao = categoriaRecomendacao;
    }

    public @NotBlank String getFonteRecomendacao() {
        return fonteRecomendacao;
    }

    public void setFonteRecomendacao(@NotBlank String fonteRecomendacao) {
        this.fonteRecomendacao = fonteRecomendacao;
    }
}
