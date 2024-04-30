package br.unesp.rc.app.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String text;
    private int numLikes;
    private int numDislikes;
    private Timestamp publishDate;

    @ManyToMany(mappedBy = "likes")
    @JsonIgnore
    private Set<Usuario> usuariosLikes = new HashSet<>();

    @ManyToMany(mappedBy = "dislikes")
    @JsonIgnore
    private Set<Usuario> usuariosDislikes = new HashSet<>();
    
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

    public Timestamp getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Comentario comentario = (Comentario) o;
        return Objects.equals(id, comentario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
