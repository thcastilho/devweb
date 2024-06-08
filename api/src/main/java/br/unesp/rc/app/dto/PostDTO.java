package br.unesp.rc.app.dto;

import java.time.LocalDate;
import java.util.List;

import br.unesp.rc.app.model.Post.Categoria;

public record PostDTO(String nome, String artista, Categoria categoria, LocalDate dataLancamento, String urlImagem, List<Long> generos) {
    
}
