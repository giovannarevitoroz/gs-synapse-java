package com.fiap.gs_synapse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "recomendacao_profissional")
public class RecomendacaoProfissional {

    @Id
    private Long idRecomendacao; // mesma PK da recomendação

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_recomendacao")
    private Recomendacao recomendacao;

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

    public Recomendacao getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(Recomendacao recomendacao) {
        this.recomendacao = recomendacao;
    }

    public @NotBlank String getCategoriaRecomendacao() {
        return categoriaRecomendacao;
    }

    public void setCategoriaRecomendacao(@NotBlank String categoriaRecomendacao) {
        this.categoriaRecomendacao = categoriaRecomendacao;
    }

    public @NotBlank String getAreaRecomendacao() {
        return areaRecomendacao;
    }

    public void setAreaRecomendacao(@NotBlank String areaRecomendacao) {
        this.areaRecomendacao = areaRecomendacao;
    }

    public @NotBlank String getFonteRecomendacao() {
        return fonteRecomendacao;
    }

    public void setFonteRecomendacao(@NotBlank String fonteRecomendacao) {
        this.fonteRecomendacao = fonteRecomendacao;
    }
}
