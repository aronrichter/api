package io.arichter.api.film;

import io.arichter.api.producer.Producer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;
    private String title;
    private String studio;
    private Boolean winner;

    @ManyToMany
    @OrderBy("name")
    @JoinTable(name = "film_producer",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<Producer> producer;
}
