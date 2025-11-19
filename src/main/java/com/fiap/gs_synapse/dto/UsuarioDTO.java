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

    @NotBlank
    private String areaAtual;

    @NotBlank
    private String areaInteresse;

    @NotBlank
    private String objetivoCarreira;

    @NotBlank
    private String nivelExperiencia;

    @NotBlank
    private String role;

    private Set<Long> competenciasIds;

    // getters e setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenhaUsuario() { return senhaUsuario; }
    public void setSenhaUsuario(String senhaUsuario) { this.senhaUsuario = senhaUsuario; }

    public String getAreaAtual() { return areaAtual; }
    public void setAreaAtual(String areaAtual) { this.areaAtual = areaAtual; }

    public String getAreaInteresse() { return areaInteresse; }
    public void setAreaInteresse(String areaInteresse) { this.areaInteresse = areaInteresse; }

    public String getObjetivoCarreira() { return objetivoCarreira; }
    public void setObjetivoCarreira(String objetivoCarreira) { this.objetivoCarreira = objetivoCarreira; }

    public String getNivelExperiencia() { return nivelExperiencia; }
    public void setNivelExperiencia(String nivelExperiencia) { this.nivelExperiencia = nivelExperiencia; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Set<Long> getCompetenciasIds() { return competenciasIds; }
    public void setCompetenciasIds(Set<Long> competenciasIds) { this.competenciasIds = competenciasIds; }
}
