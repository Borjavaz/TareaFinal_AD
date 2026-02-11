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
    private SagaService sagaService;


    @GetMapping
    public List<Saga> getAll() {
        return sagaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saga> getById(@PathVariable Long id) {
        return sagaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Saga>> getByTitulo(@PathVariable String titulo) {
        List<Saga> sagas = sagaService.sagaByTitulo(titulo);
        if (sagas == null || sagas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sagas);
    }


    @PostMapping
    public ResponseEntity<Saga> create(@RequestBody Saga saga) {
        Saga gardado = sagaService.save(saga);
        return ResponseEntity.ok(gardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sagaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sagaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}