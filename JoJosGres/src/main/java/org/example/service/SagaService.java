package org.example.service;

import org.example.model.Saga;
import org.example.repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SagaService {

    @Autowired
    private SagaRepository repo;

    public List<Saga> findAll() { return repo.findAll(); }
    public Optional<Saga> findById(Long id) { return repo.findById(id); }
    public Saga save(Saga s) { return repo.save(s); }
    public List<Saga> sagaByTitulo(String t) { return repo.findByTitulo(t); }
    public boolean existsById(Long id) { return repo.existsById(id); }
    public void deleteById(Long id) { repo.deleteById(id); }
}