package io.arichter.api.film.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmResponse {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
