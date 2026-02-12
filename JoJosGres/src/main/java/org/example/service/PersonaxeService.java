package org.example.service;

import org.example.model.Personaxe;
import org.example.repository.PersonaxeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaxeService {

    @Autowired
    private PersonaxeRepository repo;

    public List<Personaxe> findAll() { return repo.findAll(); }
    public Optional<Personaxe> findById(Long id) { return repo.findById(id); }
    public Personaxe save(Personaxe p) { return repo.save(p); }
    public boolean existsById(Long id) { return repo.existsById(id); }
    public void deleteById(Long id) { repo.deleteById(id); }
}