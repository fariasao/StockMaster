package br.com.stockmaster.controller;

import br.com.stockmaster.model.Categoria;
import br.com.stockmaster.repository.CategoriaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index() {
        log.info("Listando todas as categorias");
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria) {
        log.info("Cadastrando categoria: {}", categoria);
        return repository.save(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("Buscando categoria por id {}", id);
        return repository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando categoria com id {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        log.info("Atualizando categoria com id {}", id);
        return repository.findById(id)
                         .map(categoriaExistente -> {
                             categoriaExistente.setNome(categoria.getNome());
                             categoriaExistente.setIcone(categoria.getIcone());
                             repository.save(categoriaExistente);
                             return ResponseEntity.ok(categoriaExistente);
                         })
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
