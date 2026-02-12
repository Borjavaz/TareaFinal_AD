package org.example.controller;

import org.example.model.Saga;
import org.example.service.PersonaxeService;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(RestSagas.MAPPING)
public class RestSagas {
    public static final String MAPPING = "/postgres/sagas";

    @Autowired
    private PersonaxeService persSvc;
    @Autowired
    private SagaService sagaSvc;

    @GetMapping
    public List<Saga> getAll() {
        return sagaSvc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saga> getById(@PathVariable Long id) {
        return sagaSvc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Saga>> getByTitulo(@PathVariable String titulo) {
        List<Saga> lista = sagaSvc.sagaByTitulo(titulo);
        if (lista == null || lista.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Saga> create(@RequestBody Saga s) {
        return ResponseEntity.ok(sagaSvc.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sagaSvc.existsById(id)) return ResponseEntity.notFound().build();
        sagaSvc.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}