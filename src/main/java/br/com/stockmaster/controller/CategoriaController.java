package br.com.stockmaster.controller;

import br.com.stockmaster.model.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<Categoria> repository = new ArrayList<>();

    @GetMapping
    public List<Categoria> index() {
        log.info("Listando todas as categorias");
        return repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria) {
        log.info("Cadastrando categoria: {}", categoria);
        repository.add(categoria);
        return categoria;
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("Buscando categoria por id {}", id);
        return repository.stream()
                         .filter(categoria -> categoria.getId().equals(id))
                         .findFirst()
                         .map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando categoria com id {}", id);
        var categoriaEncontrada = getCategoriaById(id);

        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.remove(categoriaEncontrada.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        log.info("Atualizando categoria com id {}", id);

        var categoriaEncontrada = getCategoriaById(id);
        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var categoriaAtual = categoriaEncontrada.get();
        categoriaAtual.setNome(categoria.getNome());
        categoriaAtual.setIcone(categoria.getIcone());

        return ResponseEntity.ok(categoriaAtual);
    }

    private Optional<Categoria> getCategoriaById(Long id) {
        return repository.stream()
                         .filter(c -> c.getId().equals(id))
                         .findFirst();
    }
}
