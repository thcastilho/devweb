package br.unesp.rc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Genero;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.GeneroRepository;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;

    public Genero getGeneroById(Long id) {
        Genero genero = generoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Genero not found"));
        
        return genero;
    }

    public List<Genero> getAllGeneros() {
        List<Genero> generos = (List<Genero>)generoRepository.findAll();
        return generos;
    }

    public Genero createGenero(Usuario usuario, Genero genero) {
        genero.setUsuarioGenero(usuario);

        return generoRepository.save(genero);
    }

    public Genero updateGenero(Long idGenero, Genero genero) {
        Genero generoSalvo = generoRepository.findById(idGenero)
            .orElseThrow(() -> new IllegalArgumentException("Genero not found"));
        
        genero.setUsuarioGenero(generoRepository.findById(idGenero).get().getUsuarioGenero());
        genero.setId(idGenero);
        generoSalvo = generoRepository.save(genero);

        return generoSalvo;
    }

    public void deleteGenero(Long idGenero) {
        Genero genero = generoRepository.findById(idGenero)
            .orElseThrow(() -> new IllegalArgumentException("Genero not found"));
        
        generoRepository.delete(genero);
    }
}
