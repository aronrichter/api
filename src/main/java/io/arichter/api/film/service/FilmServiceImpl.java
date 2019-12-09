package io.arichter.api.film.service;

import io.arichter.api.film.Film;
import io.arichter.api.film.FilmRepository;
import io.arichter.api.film.payload.FilmResponse;
import io.arichter.api.film.payload.FilmsResponse;
import io.arichter.api.producer.Producer;
import io.arichter.api.producer.service.ProducerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final static String SEPARATOR = ";";
    private final static String PRODUCER_SEPARATOR_REGEX = "[,]|\\band";

    private FilmRepository filmRepository;

    private ProducerService producerService;

    public FilmServiceImpl(FilmRepository filmRepository, ProducerService producerService) {
        this.filmRepository = filmRepository;
        this.producerService = producerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void processFile() throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader("movielist.csv"));

        List<String> films = csvReader.lines().skip(1L).collect(Collectors.toList());

        films.forEach(f -> save(f.split(SEPARATOR)));
    }

    private void save(String[] colunas) {
        Film film = new Film();

        film.setYear(Integer.parseInt(colunas[0]));
        film.setTitle(colunas[1]);
        film.setStudio(colunas[2]);
        film.setProducer(saveProducer(colunas[3]));

        if (colunas.length == 5) {
            film.setWinner(colunas[4].equals("yes"));
        } else {
            film.setWinner(false);
        }

        filmRepository.save(film);
    }

    private List<Producer> saveProducer(String producerString) {
        String[] producerList = producerString.split(PRODUCER_SEPARATOR_REGEX);

        List<Producer> producersToFilm = new ArrayList<>();

        for (String s : producerList) {
            List<Producer> producers = producerService.findByName(s.trim());

            if (producers == null || producers.isEmpty()) {
                Producer producerCreated = producerService.create(s.trim());

                producersToFilm.add(producerCreated);
            } else {
                producersToFilm.add(producers.get(0));
            }
        }

        return producersToFilm;
    }

    @Override
    public FilmsResponse getWinners() {
        List<Producer> producers = producerService.findAll();

        HashMap<Producer, List<Integer>> producerArrayListHashMap = new HashMap<>();

        for (Producer producer : producers) {
            List<Film> films = filmRepository.findByWinnerTrueAndProducer(producer);

            if (films != null && !films.isEmpty()) {
                producerArrayListHashMap.put(producer, films.stream()
                        .map(Film::getYear)
                        .collect(Collectors.toList()));
            }
        }

        FilmsResponse filmsResponse = new FilmsResponse();

        return filmsResponse;
    }
}
