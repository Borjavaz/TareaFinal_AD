package org.example;

import org.example.model.Losjojos;
import org.example.model.Personaxe;
import org.example.model.Saga;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private ConexionPostgresService conPostgres;
    @Autowired
    private ConexionMongoService conMongo;
    @Autowired
    private JsonService jsonService;

    public void executar() {
        ArrayList<Personaxe> lista = new ArrayList<>();
        Personaxe p1 = new Personaxe();
        p1.setNome("Giorno Giovanna");
        p1.setStand("Gold Experience");
        lista.add(p1);

        Personaxe p2 = new Personaxe();
        p2.setNome("Bruno Bucciarati");
        p2.setStand("Sticky Fingers");
        lista.add(p2);

        Personaxe p3 = new Personaxe();
        p3.setNome("Guido Mista");
        p3.setStand("Sex Pistols");
        lista.add(p3);

        Saga s = new Saga();
        s.setTitulo("Vento Aureo");
        s.setParte(5);
        s.setAmbientacion("Italia");
        s.setAnoinicio(2001);
        s.setPersonaxes(lista);
        s = conPostgres.crearSaga(s);

        Saga s2 = conPostgres.sagaPorID(2L);
        conMongo.crearSaga(s2);

        Saga sC = conPostgres.sagaPorTitulo("Stardust Crusaders");
        conMongo.crearSaga(sC);

        List<Saga> todas = conPostgres.buscarSagas();
        for (Saga sag : todas) {
            conMongo.crearSaga(sag);
        }

        Losjojos jojos = new Losjojos();
        jojos.setSagas(todas);
        conMongo.crearJojos(jojos);

        List<Losjojos> listaJojos = conMongo.buscarLosJojos();
        List<Saga> listaSagas = conMongo.buscarSagas();

        jsonService.exportarLosJojosJson(listaJojos);
        jsonService.exportarSagasJson(listaSagas);

        conPostgres.borrarSaga(s.getId());
        conMongo.borrarSaga(s.getId());
    }
}