package org.example.service;

import com.google.gson.Gson;
import org.example.model.Saga;
import org.example.repository.SagaRepository;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class SagaService {
    private SagaRepository repo;

    public SagaService(SagaRepository repo) {
        this.repo = repo;
    }

    public List<Saga> findAll() { return repo.findAll(); }
    public Optional<Saga> findById(Long id) { return repo.findById(id); }
    public Saga save(Saga s) { return repo.save(s); }
    public boolean existsById(Long id) { return repo.existsById(id); }
    public List<Saga> sagaByTitulo(String t) { return repo.findByTitulo(t); }
    public void deleteById(Long id) { repo.deleteById(id); }

    public void exportarJson() {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("src/main/java/org/example/Json/Sagas.json")) {
            fw.write(gson.toJson(findAll()));
        } catch (Exception e) {
            System.out.println("erro export: " + e.getMessage());
        }
    }
}