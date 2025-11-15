package com.fiap.gs_synapse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecomendacao;

    @NotNull
    private LocalDate dataRecomendacao;

    @NotBlank
    @Column(length = 1000)
    private String descricaoRecomendacao;

    @NotBlank
    @Column(length = 1000)
    private String promptUsado;

    @NotBlank
    @Column(length = 100)
    private String tituloRecomendacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;

    @OneToOne(mappedBy = "recomendacao", cascade = CascadeType.ALL)
    private RecomendacaoProfissional recomendacaoProfissional;

    @OneToOne(mappedBy = "recomendacao", cascade = CascadeType.ALL)
    private RecomendacaoSaude recomendacaoSaude;

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

    public @NotBlank String getDescricaoRecomendacao() {
        return descricaoRecomendacao;
    }

    public void setDescricaoRecomendacao(@NotBlank String descricaoRecomendacao) {
        this.descricaoRecomendacao = descricaoRecomendacao;
    }

    public @NotBlank String getPromptUsado() {
        return promptUsado;
    }

    public void setPromptUsado(@NotBlank String promptUsado) {
        this.promptUsado = promptUsado;
    }

    public @NotBlank String getTituloRecomendacao() {
        return tituloRecomendacao;
    }

    public void setTituloRecomendacao(@NotBlank String tituloRecomendacao) {
        this.tituloRecomendacao = tituloRecomendacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RecomendacaoProfissional getRecomendacaoProfissional() {
        return recomendacaoProfissional;
    }

    public void setRecomendacaoProfissional(RecomendacaoProfissional recomendacaoProfissional) {
        this.recomendacaoProfissional = recomendacaoProfissional;
    }

    public RecomendacaoSaude getRecomendacaoSaude() {
        return recomendacaoSaude;
    }

    public void setRecomendacaoSaude(RecomendacaoSaude recomendacaoSaude) {
        this.recomendacaoSaude = recomendacaoSaude;
    }
}

