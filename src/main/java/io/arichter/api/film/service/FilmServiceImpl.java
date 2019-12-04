package io.arichter.api.film.service;

import io.arichter.api.film.Film;
import io.arichter.api.film.FilmRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl {

    private final static String FAKE_SEPARATOR = ",";
    private final static String SEPARATOR = ";";

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void processFile() throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader("movielist.csv"));

        List<String> films = csvReader.lines().skip(1L).collect(Collectors.toList());

        films.forEach(f -> save(f.replaceAll(FAKE_SEPARATOR, SEPARATOR).split(SEPARATOR)));
    }

    public void save(String[] colunas) {
        Film film = new Film();

        film.setYear(Integer.getInteger(colunas[0]));
        film.setTitle(colunas[1]);
        film.setStudio(colunas[2]);
        film.setProducer(colunas[3]);

        if (colunas.length == 5) {
            film.setWinner(colunas[4].equals("yes"));
        }

        filmRepository.save(film);
    }

}
