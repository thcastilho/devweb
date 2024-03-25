package br.unesp.rc.app.model;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "genres")
    private List<String> genres = new ArrayList<>();

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "average_rating")
    private float averageRating;

    @Column(name = "publish_date")
    private Timestamp publishDate;

    @Column(name = "image")
    private String image;

    @org.hibernate.annotations.ForeignKey(name = "usuario_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioPost;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", length = 25)
    private Categoria categoria;

    public enum Categoria {
        MUSICA, ALBUM
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
