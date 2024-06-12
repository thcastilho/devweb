package br.unesp.rc.app.dto;

import java.time.LocalDate;

public record CreateAvaliacaoDTO(String text, float numStars, String publishDate) {
    
}
