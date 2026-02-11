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

    private SagaRepository sagaRepository;

    public SagaService(SagaRepository sagaRepository) {
        this.sagaRepository = sagaRepository;
    }

    public List<Saga> findAll() {
        return sagaRepository.findAll();
    }

    public Optional<Saga> findById(Long id) {
        return sagaRepository.findById(id);
    }

    public Saga save(Saga saga) {
        return sagaRepository.save(saga);
    }

    public boolean existsById(Long id) {
        return sagaRepository.existsById(id);
    }

    public List<Saga> sagaByTitulo(String titulo){
        return sagaRepository.findByTitulo(titulo);
    }

    public void deleteById(Long id) {
        sagaRepository.deleteById(id);
    }

    public void exportarJson(){
        Gson gson = new Gson();
        List<Saga> sagas = findAll();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/Sagas.json")){
            String json = gson.toJson(sagas);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}