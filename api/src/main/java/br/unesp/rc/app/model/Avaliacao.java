package br.unesp.rc.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("avaliacao")
@Getter
@Setter
public class Avaliacao extends Comentario {
    @Column(name = "num_stars")
    private float numStars;

    public Avaliacao() {}

    public Avaliacao(Long id, String text, int numLikes, String publishDate, float numStars) {
        super.setId(id);
        super.setText(text);
        super.setPublishDate(publishDate);
        this.numStars = numStars;
    }

    @org.hibernate.annotations.ForeignKey(name = "usuario_avaliacao_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioAvaliacao;

    @OneToMany(mappedBy = "avaliacaoResposta", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();

    @org.hibernate.annotations.ForeignKey(name = "post_avaliacao_id")    
    @ManyToOne
    @JsonIgnore
    private Post avaliacaoPost;
}
