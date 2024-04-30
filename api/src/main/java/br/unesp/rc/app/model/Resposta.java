package br.unesp.rc.app.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("resposta")
@Getter
@Setter
public class Resposta extends Comentario {
    public Resposta(){}

    public Resposta(Long id, String text, int numLikes, Timestamp publishDate) {
        super.setId(id);
        super.setText(text);
        super.setNumLikes(numLikes);
        super.setPublishDate(publishDate);
    }

    @org.hibernate.annotations.ForeignKey(name = "usuario_resposta_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioResposta;

    @org.hibernate.annotations.ForeignKey(name = "avaliacao_resposta_id")    
    @ManyToOne
    @JsonIgnore
    private Avaliacao avaliacaoResposta;
}
