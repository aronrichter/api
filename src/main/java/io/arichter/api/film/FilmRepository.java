package io.arichter.api.film;

import io.arichter.api.producer.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    List<Film> findByWinnerTrueOrderByProducer();

    List<Film> findByWinnerTrueAndProducer(Producer producer);
}
