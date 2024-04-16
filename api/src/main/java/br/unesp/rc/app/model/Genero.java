package br.unesp.rc.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @org.hibernate.annotations.ForeignKey(name = "usuario_genero_id")
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioGenero;
    
    @ManyToMany(mappedBy = "generos")
    private List<Post> posts = new ArrayList<>();

    public Genero() {}

    public Genero(Long id, String name, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    //listarGeneros
}
