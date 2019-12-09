package io.arichter.api.producer.service;

import io.arichter.api.producer.Producer;

import java.util.List;

public interface ProducerService {

    Producer create(String nome);

    List<Producer> findByName(String name);

    List<Producer> findAll();
}
