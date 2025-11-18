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

    @NotBlank
    @Column(nullable = false)
    private String role; // <-- O QUE FALTAVA!!

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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
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

    public String getObjetivoCarreira() {
        return objetivoCarreira;
    }

    public void setObjetivoCarreira(String objetivoCarreira) {
        this.objetivoCarreira = objetivoCarreira;
    }

    public String getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(String nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }
}
