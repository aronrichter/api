package io.arichter.api.contracts;

import io.arichter.api.film.payload.FilmResponse;
import io.arichter.api.film.payload.FilmsResponse;
import io.arichter.api.film.service.FilmService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class Films {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private FilmService filmService;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);

        FilmResponse filmResponse1 = new FilmResponse();
        filmResponse1.setInterval(1);
        filmResponse1.setPreviousWin(1990);
        filmResponse1.setFollowingWin(1991);
        filmResponse1.setProducer("Joel Silver");

        FilmResponse filmResponse2 = new FilmResponse();
        filmResponse1.setInterval(13);
        filmResponse1.setPreviousWin(2002);
        filmResponse1.setFollowingWin(2015);
        filmResponse1.setProducer("Matthew Vaughn");

        FilmsResponse filmsResponse = new FilmsResponse();
        filmsResponse.setMin(filmResponse1);
        filmsResponse.setMax(filmResponse2);

        when(filmService.getWinners()).thenReturn(filmsResponse);
    }
}
