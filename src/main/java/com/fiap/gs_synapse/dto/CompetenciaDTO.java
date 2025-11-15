package com.fiap.gs_synapse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CompetenciaDTO {

    private Long idCompetencia;

    @NotBlank
    @Size(max = 100)
    private String nomeCompetencia;

    @NotBlank
    @Size(max = 50)
    private String categoriaCompetencia;

    @NotBlank
    @Size(max = 255)
    private String descricaoCompetencia;

    // getters e setters

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public @NotBlank @Size(max = 100) String getNomeCompetencia() {
        return nomeCompetencia;
    }

    public void setNomeCompetencia(@NotBlank @Size(max = 100) String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    public @NotBlank @Size(max = 50) String getCategoriaCompetencia() {
        return categoriaCompetencia;
    }

    public void setCategoriaCompetencia(@NotBlank @Size(max = 50) String categoriaCompetencia) {
        this.categoriaCompetencia = categoriaCompetencia;
    }

    public @NotBlank @Size(max = 255) String getDescricaoCompetencia() {
        return descricaoCompetencia;
    }

    public void setDescricaoCompetencia(@NotBlank @Size(max = 255) String descricaoCompetencia) {
        this.descricaoCompetencia = descricaoCompetencia;
    }


}
