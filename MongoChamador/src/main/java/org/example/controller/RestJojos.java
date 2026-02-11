package org.example.controller;


import org.example.model.Losjojos;
import org.example.model.Saga;
import org.example.service.LosJoJosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(RestJojos.MAPPING)
public class RestJojos {
    public static final String MAPPING = "/Mongo/jojos";

    @Autowired
    private LosJoJosService losJoJosService;

    @GetMapping
    public List<Losjojos> getAll() {
        return losJoJosService.findAll();
    }

    @PostMapping
    public ResponseEntity<Losjojos> create(@RequestBody Losjojos losjojos) {
        Losjojos gardado = losJoJosService.save(losjojos);
        return ResponseEntity.ok(gardado);
    }

}