package com.fiap.gs_synapse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "recomendacao_saude")
public class RecomendacaoSaude {

    @Id
    private Long idRecomendacao;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_recomendacao")
    private Recomendacao recomendacao;

    @NotBlank
    private String tipoSaude;

    @NotBlank
    private String nivelAlerta;

    @NotBlank
    @Column(length = 1000)
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

    public Recomendacao getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(Recomendacao recomendacao) {
        this.recomendacao = recomendacao;
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
