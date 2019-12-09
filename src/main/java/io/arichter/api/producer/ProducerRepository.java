package io.arichter.api.producer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    List<Producer> findByName(String name);
}
