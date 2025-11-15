package com.fiap.gs_synapse.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RegistroBemEstarDTO {

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

    private String observacaoRegistro;

    private Long usuarioId;

    // getters e setters

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public @NotNull LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(@NotNull LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public @NotBlank String getHumorRegistro() {
        return humorRegistro;
    }

    public void setHumorRegistro(@NotBlank String humorRegistro) {
        this.humorRegistro = humorRegistro;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
