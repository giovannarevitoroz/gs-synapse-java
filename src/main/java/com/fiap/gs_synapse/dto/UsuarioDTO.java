package com.fiap.gs_synapse.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

public class UsuarioDTO {

    private Long idUsuario;

    @NotBlank
    @Size(max = 100)
    private String nomeUsuario;

    @NotBlank
    private String senhaUsuario;

    private String areaAtual;
    private String areaInteresse;
    private String objetivoCarreira;
    private String nivelExperiencia;
    private Set<Long> competenciasIds;

    // getters e setters


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotBlank @Size(max = 100) String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(@NotBlank @Size(max = 100) String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public @NotBlank String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(@NotBlank String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getAreaAtual() {
        return areaAtual;
    }

    public void setAreaAtual(String areaAtual) {
        this.areaAtual = areaAtual;
    }

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public String getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(String nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public String getObjetivoCarreira() {
        return objetivoCarreira;
    }

    public void setObjetivoCarreira(String objetivoCarreira) {
        this.objetivoCarreira = objetivoCarreira;
    }

    public Set<Long> getCompetenciasIds() {
        return competenciasIds;
    }

    public void setCompetenciasIds(Set<Long> competenciasIds) {
        this.competenciasIds = competenciasIds;
    }
}
