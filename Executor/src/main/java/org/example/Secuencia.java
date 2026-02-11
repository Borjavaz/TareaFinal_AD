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
    private ConexionPostgresService conexionPostgresService;
    @Autowired
    private ConexionMongoService conexionMongoService;
    @Autowired
    private JsonService jsonService;

    public void executar() {

        ArrayList<Personaxe> ps = new ArrayList<>();
        Personaxe p1 = new Personaxe();
        p1.setNome("Giorno Giovanna");
        p1.setStand("Gold Experience");
        ps.add(p1);

        Personaxe p2 = new Personaxe();
        p2.setNome("Bruno Bucciarati");
        p2.setStand("Sticky Fingers");
        ps.add(p2);

        Personaxe p3 = new Personaxe();
        p3.setNome("Guido Mista");
        p3.setStand("Sex Pistols");
        ps.add(p3);

        Saga saga = new Saga();
        saga.setTitulo("Vento Aureo");
        saga.setParte(5);
        saga.setAmbientacion("Italia");
        saga.setAnoinicio(2001);
        saga.setPersonaxes(ps);

        saga = conexionPostgresService.crearSaga(saga);


        Saga saga2 = conexionPostgresService.sagaPorID(2L);
        conexionMongoService.crearSaga(saga2);

        Saga sagaC = conexionPostgresService.sagaPorTitulo("Stardust Crusaders");
        conexionMongoService.crearSaga(sagaC);

        List<Saga> sagas = conexionPostgresService.buscarSagas();
        for (Saga s:sagas){
            conexionMongoService.crearSaga(s);
        }

        Losjojos losjojos = new Losjojos();
        losjojos.setSagas(sagas);
        conexionMongoService.crearJojos(losjojos);

        List<Losjojos> losjojos1 = conexionMongoService.buscarLosJojos();
        List<Saga> sagas1 = conexionMongoService.buscarSagas();

        jsonService.exportarLosJojosJson(losjojos1);
        jsonService.exportarSagasJson(sagas1);

        conexionPostgresService.borrarSaga(saga.getId());
        conexionMongoService.borrarSaga(saga.getId());

    }
}