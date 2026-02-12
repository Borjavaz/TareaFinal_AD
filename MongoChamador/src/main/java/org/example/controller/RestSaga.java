package org.example.controller;

import org.example.model.Saga;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(RestSaga.MAPPING)
public class RestSaga {
    public static final String MAPPING = "/Mongo/sagas";

    @Autowired
    private SagaService svc;

    @GetMapping
    public List<Saga> getAll() {
        return svc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saga> getById(@PathVariable Long id) {
        return svc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Saga>> getByTitulo(@PathVariable String titulo) {
        List<Saga> lista = svc.sagaByTitulo(titulo);
        if (lista == null || lista.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Saga> create(@RequestBody Saga s) {
        return ResponseEntity.ok(svc.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!svc.existsById(id)) return ResponseEntity.notFound().build();
        svc.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}