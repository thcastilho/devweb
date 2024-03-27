package br.unesp.rc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.app.model.Produto;
import br.unesp.rc.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Produto>> getAllProducts() {
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();

        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);

        return new ResponseEntity<>(produtoSalvo, HttpStatus.OK);
    }
}
