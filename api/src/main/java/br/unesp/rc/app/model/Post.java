package br.unesp.rc.app.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    private String artist;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "average_rating")
    private float averageRating;

    @Column(name = "publish_date")
    private Timestamp publishDate;

    @Column(name = "image")
    private String image;

    @org.hibernate.annotations.ForeignKey(name = "usuario_post_id")
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioPost;

    @OneToMany(mappedBy = "avaliacaoPost", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", length = 25)
    private Categoria categoria;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "posts_generos", uniqueConstraints = @UniqueConstraint(columnNames = { "post_id",
            "genero_id" }, name = "unique_genero_post"), joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id", table = "posts", unique = false), inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id", table = "generos", unique = false))
    private List<Genero> generos = new ArrayList<Genero>();

    public enum Categoria {
        MUSICA, ALBUM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void assignGenre(Genero genero) {
        generos.add(genero);
    }

}
