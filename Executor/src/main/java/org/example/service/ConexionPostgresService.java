package org.example.service;

import org.example.model.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class ConexionPostgresService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PG_SAGA_URL = "http://localhost:8081/postgres/sagas";

    public List<Saga> buscarSagas() {
        try {
            ResponseEntity<List<Saga>> resp = restTemplate.exchange(
                    PG_SAGA_URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {});
            return resp.getBody() != null ? resp.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("erro: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean borrarSaga(Long id) {
        try {
            String url = PG_SAGA_URL + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("fallo delete: " + e.getMessage());
            return false;
        }
    }

    public Saga crearSaga(Saga saga) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Saga> req = new HttpEntity<>(saga, headers);
            ResponseEntity<Saga> resp = restTemplate.exchange(
                    PG_SAGA_URL, HttpMethod.POST, req, Saga.class);
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("erro post: " + e.getMessage());
            return null;
        }
    }

    public Saga sagaPorID(Long id) {
        try {
            String url = PG_SAGA_URL + "/" + id;
            ResponseEntity<Saga> resp = restTemplate.exchange(url, HttpMethod.GET, null, Saga.class);
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("erro get id: " + e.getMessage());
            return null;
        }
    }

    public Saga sagaPorTitulo(String titulo) {
        try {
            String url = PG_SAGA_URL + "/titulo/" + titulo;
            ResponseEntity<List<Saga>> resp = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {});
            return resp.getBody().get(0);
        } catch (HttpClientErrorException e) {
            System.out.println("erro get titulo: " + e.getMessage());
            return null;
        }
    }
}