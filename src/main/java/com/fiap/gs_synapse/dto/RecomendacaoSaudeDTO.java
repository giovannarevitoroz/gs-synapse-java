package com.fiap.gs_synapse.dto;

import jakarta.validation.constraints.NotBlank;

public class RecomendacaoSaudeDTO {

    private Long idRecomendacao;

    @NotBlank
    private String tipoSaude;

    @NotBlank
    private String nivelAlerta;

    @NotBlank
    private String mensagemSaude;

    // getters e setters

    public Long getIdRecomendacao() {
        return idRecomendacao;
    }

    public void setIdRecomendacao(Long idRecomendacao) {
        this.idRecomendacao = idRecomendacao;
    }

    public @NotBlank String getTipoSaude() {
        return tipoSaude;
    }

    public void setTipoSaude(@NotBlank String tipoSaude) {
        this.tipoSaude = tipoSaude;
    }

    public @NotBlank String getNivelAlerta() {
        return nivelAlerta;
    }

    public void setNivelAlerta(@NotBlank String nivelAlerta) {
        this.nivelAlerta = nivelAlerta;
    }

    public @NotBlank String getMensagemSaude() {
        return mensagemSaude;
    }

    public void setMensagemSaude(@NotBlank String mensagemSaude) {
        this.mensagemSaude = mensagemSaude;
    }
}
