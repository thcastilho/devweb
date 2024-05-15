package br.unesp.rc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Genero;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.GeneroRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public Genero getGeneroById(Long id) {
        Genero genero = generoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Genero not found"));
        
        return genero;
    }

    public List<Genero> getAllGeneros() {
        System.out.println("vai buscar");
        List<Genero> generos = (List<Genero>)generoRepository.findAll();
        System.out.println("voltou");
        return generos;
    }

    public Genero createGenero(String token, Genero genero) {
        Usuario usuario = usuarioRepository.findByLogin(tokenService.validateToken(token.replace("Bearer ", "")));

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
