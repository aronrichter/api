package io.arichter.api.film.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmsResponse {

    private FilmResponse min;
    private FilmResponse max;
}
