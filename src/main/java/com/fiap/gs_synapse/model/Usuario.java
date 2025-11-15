package com.fiap.gs_synapse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String nomeUsuario;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String senhaUsuario;

    @NotBlank
    @Column(nullable = false)
    private String areaAtual;

    @NotBlank
    @Column(nullable = false)
    private String areaInteresse;

    @NotBlank
    @Column(nullable = false)
    private String objetivoCarreira;

    @NotBlank
    @Column(nullable = false)
    private String nivelExperiencia;

    @ManyToMany
    @JoinTable(
            name = "usuario_competencia",
            joinColumns = @JoinColumn(name = "usuario_id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "competencia_id_competencia")
    )
    private Set<Competencia> competencias;

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

    public @NotBlank @Size(max = 255) String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(@NotBlank @Size(max = 255) String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public @NotBlank String getAreaAtual() {
        return areaAtual;
    }

    public void setAreaAtual(@NotBlank String areaAtual) {
        this.areaAtual = areaAtual;
    }

    public @NotBlank String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(@NotBlank String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public @NotBlank String getObjetivoCarreira() {
        return objetivoCarreira;
    }

    public void setObjetivoCarreira(@NotBlank String objetivoCarreira) {
        this.objetivoCarreira = objetivoCarreira;
    }

    public @NotBlank String getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(@NotBlank String nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public Set<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }
}
