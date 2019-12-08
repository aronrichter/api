package io.arichter.api.film;

import io.arichter.api.film.payload.FilmsResponse;
import io.arichter.api.film.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("films")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public FilmsResponse get() {
        return filmService.getWinners();
    }
}
