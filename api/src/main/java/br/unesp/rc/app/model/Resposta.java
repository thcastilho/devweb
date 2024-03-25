package br.unesp.rc.app.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "respostas")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return Objects.equals(id, resposta.id);
    }

    @org.hibernate.annotations.ForeignKey(name = "usuario_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioResposta;

    @org.hibernate.annotations.ForeignKey(name = "avaliacao_id")    
    @ManyToOne
    @JsonIgnore
    private Usuario avaliacaoResposta;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
