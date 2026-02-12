package org.example.service;

import com.google.gson.Gson;
import org.example.model.Losjojos;
import org.example.model.Saga;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.util.List;

@Service
public class JsonService {

    public void exportarLosJojosJson(List<Losjojos> lista) {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("src/main/java/org/example/Json/LosJojos.json")) {
            fw.write(gson.toJson(lista));
        } catch (Exception e) {
            System.out.println("erro export: " + e.getMessage());
        }
    }

    public void exportarSagasJson(List<Saga> lista) {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("src/main/java/org/example/Json/Sagas.json")) {
            fw.write(gson.toJson(lista));
        } catch (Exception e) {
            System.out.println("erro export: " + e.getMessage());
        }
    }
}