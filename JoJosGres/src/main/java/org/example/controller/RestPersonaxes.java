package org.example.controller;

import org.example.model.Saga;
import org.example.model.Personaxe;
import org.example.service.SagaService;
import org.example.service.PersonaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(RestPersonaxes.MAPPING)
public class RestPersonaxes {

    public static final String MAPPING = "/postgres/personaxes";

    @Autowired
    private SagaService sagaSvc;
    @Autowired
    private PersonaxeService persSvc;

    @GetMapping
    public List<Personaxe> getAll() {
        return persSvc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaxe> getById(@PathVariable Long id) {
        return persSvc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personaxe> create(@RequestBody Personaxe p) {
        if (p.getSaga() != null && p.getSaga().getId() != null) {
            Saga eq = sagaSvc.findById(p.getSaga().getId()).orElse(null);
            if (eq == null) return ResponseEntity.badRequest().build();
            p.setSaga(eq);
        }
        return ResponseEntity.ok(persSvc.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!persSvc.existsById(id)) return ResponseEntity.notFound().build();
        persSvc.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}