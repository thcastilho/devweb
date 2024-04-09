package br.unesp.rc.app.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("avaliacoes")
@Getter @Setter
@Table(name = "avaliacoes")
public class Avaliacao extends Comentario {
    @Column(name = "num_stars")
    private int numStars;

    public Avaliacao() {}

    public Avaliacao(Long id, String text, int numLikes, Timestamp publishDate, int numStars) {
        super.setId(id);
        super.setText(text);
        super.setPublishDate(publishDate);
        this.numStars = numStars;
    }

    @org.hibernate.annotations.ForeignKey(name = "usuario_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioAvaliacao;

    @OneToMany(mappedBy = "avaliacaoResposta", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();
}
