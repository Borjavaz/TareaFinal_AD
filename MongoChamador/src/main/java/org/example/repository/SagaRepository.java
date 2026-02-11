package org.example.repository;

import org.example.model.Saga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaRepository extends MongoRepository<Saga,Long> {
    List<Saga> findByTitulo(String titulo);
}