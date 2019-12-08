package io.arichter.api.film.service;

import io.arichter.api.film.Film;
import io.arichter.api.film.FilmRepository;
import io.arichter.api.film.payload.FilmResponse;
import io.arichter.api.film.payload.FilmsResponse;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final static String SEPARATOR = ";";

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
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
        film.setProducer(colunas[3]);

        if (colunas.length == 5) {
            film.setWinner(colunas[4].equals("yes"));
        } else {
            film.setWinner(false);
        }

        filmRepository.save(film);
    }

    @Override
    public FilmsResponse getWinners() {
        List<Film> films = filmRepository.findByWinnerTrueOrderByProducer();

        FilmResponse max = getBiggestIntervalWinner(films);

        return null;
    }

    private FilmResponse getBiggestIntervalWinner(List<Film> films) {
        Map<String, List<Integer>> winners = new HashMap<>();

        for (Film film : films) {
            winners.put(film.getProducer(), films.stream().filter(f -> f.getProducer().equals(film.getProducer())).map(Film::getYear).collect(Collectors.toList()));
        }

        return null;
    }

    private FilmResponse getQuickestIntervalWinner() {
        return null;
    }


}
