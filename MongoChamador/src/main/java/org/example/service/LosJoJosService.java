package org.example.service;

import com.google.gson.Gson;
import org.example.model.Losjojos;
import org.example.repository.LosJojosRepository;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.util.List;

@Service
public class LosJoJosService {
    private LosJojosRepository repo;

    public LosJoJosService(LosJojosRepository repo) {
        this.repo = repo;
    }

    public Losjojos save(Losjojos j) { return repo.save(j); }
    public List<Losjojos> findAll() { return repo.findAll(); }

    public void exportarJson() {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("src/main/java/org/example/Json/LosJojos.json")) {
            fw.write(gson.toJson(repo.findAll()));
        } catch (Exception e) {
            System.out.println("erro export: " + e.getMessage());
        }
    }
}