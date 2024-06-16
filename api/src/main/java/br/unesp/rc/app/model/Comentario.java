package br.unesp.rc.app.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@EntityListeners(AuditingEntityListener.class)
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private int numLikes;
    private int numDislikes;

    @ManyToMany(mappedBy = "likes")
    @JsonIgnore
    private Set<Usuario> usuariosLikes = new HashSet<>();

    @ManyToMany(mappedBy = "dislikes")
    @JsonIgnore
    private Set<Usuario> usuariosDislikes = new HashSet<>();

    @CreatedDate
    @Column(name = "data_criacao")
    private String dataCriacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumLikes() {
        return this.numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumDislikes() {
        return this.numDislikes;
    }

    public void setNumDislikes(int numDislikes) {
        this.numDislikes = numDislikes;
    }

    public Set<Usuario> getUsuariosLikes() {
        return usuariosLikes;
    }

    public void setUsuariosLikes(Set<Usuario> usuariosLikes) {
        this.usuariosLikes = usuariosLikes;
    }

    public Set<Usuario> getUsuariosDislikes() {
        return usuariosDislikes;
    }

    public void setUsuariosDislikes(Set<Usuario> usuariosDislikes) {
        this.usuariosDislikes = usuariosDislikes;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Comentario comentario = (Comentario) o;
        return Objects.equals(id, comentario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
