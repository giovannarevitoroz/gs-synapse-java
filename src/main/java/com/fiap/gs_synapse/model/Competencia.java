package com.fiap.gs_synapse.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "competencia")
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetencia;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nomeCompetencia;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String categoriaCompetencia;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricaoCompetencia;

    @ManyToMany(mappedBy = "competencias")
    private Set<Usuario> usuarios;

    // getters e setters

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public @NotBlank String getNomeCompetencia() {
        return nomeCompetencia;
    }

    public void setNomeCompetencia(@NotBlank String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    public @NotBlank String getCategoriaCompetencia() {
        return categoriaCompetencia;
    }

    public void setCategoriaCompetencia(@NotBlank String categoriaCompetencia) {
        this.categoriaCompetencia = categoriaCompetencia;
    }

    public @NotBlank String getDescricaoCompetencia() {
        return descricaoCompetencia;
    }

    public void setDescricaoCompetencia(@NotBlank String descricaoCompetencia) {
        this.descricaoCompetencia = descricaoCompetencia;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
