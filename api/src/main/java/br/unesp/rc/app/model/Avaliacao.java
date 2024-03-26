package br.unesp.rc.app.model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "avaliacoes")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;

    @Column(name = "num_stars")
    private int numStars;

    @Column(name = "num_likes")
    private int numLikes;

    @org.hibernate.annotations.ForeignKey(name = "usuario_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioAvaliacao;

    @OneToMany(mappedBy = "avaliacaoResposta", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
