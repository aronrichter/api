package io.arichter.api.producer.service;

import io.arichter.api.producer.Producer;
import io.arichter.api.producer.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    private ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer create(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }

    @Override
    public List<Producer> findByName(String name) {
        return producerRepository.findByName(name);
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }
}
