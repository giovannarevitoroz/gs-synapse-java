package com.fiap.gs_synapse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro_bem_estar")
public class RegistroBemEstar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @NotNull
    private LocalDate dataRegistro;

    @NotBlank
    private String humorRegistro;

    @Min(0)
    @Max(24)
    private int horasSono;

    @Min(0)
    @Max(24)
    private int horasTrabalho;

    @Min(1)
    @Max(10)
    private int nivelEnergia;

    @Min(1)
    @Max(10)
    private int nivelEstresse;

    @Column(length = 255)
    private String observacaoRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;

    // getters e setters

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public @NotBlank String getHumorRegistro() {
        return humorRegistro;
    }

    public void setHumorRegistro(@NotBlank String humorRegistro) {
        this.humorRegistro = humorRegistro;
    }

    public @NotNull LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(@NotNull LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Min(0)
    @Max(24)
    public int getHorasSono() {
        return horasSono;
    }

    public void setHorasSono(@Min(0) @Max(24) int horasSono) {
        this.horasSono = horasSono;
    }

    @Min(0)
    @Max(24)
    public int getHorasTrabalho() {
        return horasTrabalho;
    }

    public void setHorasTrabalho(@Min(0) @Max(24) int horasTrabalho) {
        this.horasTrabalho = horasTrabalho;
    }

    @Min(1)
    @Max(10)
    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(@Min(1) @Max(10) int nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    @Min(1)
    @Max(10)
    public int getNivelEstresse() {
        return nivelEstresse;
    }

    public void setNivelEstresse(@Min(1) @Max(10) int nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }

    public String getObservacaoRegistro() {
        return observacaoRegistro;
    }

    public void setObservacaoRegistro(String observacaoRegistro) {
        this.observacaoRegistro = observacaoRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
