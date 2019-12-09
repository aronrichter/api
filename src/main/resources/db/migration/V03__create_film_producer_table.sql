CREATE TABLE film_producer(
    film_id       INT NOT NULL,
    producer_id   INT NOT NULL
);

ALTER TABLE film_producer
    ADD FOREIGN KEY (film_id)
        REFERENCES film (id);

ALTER TABLE film_producer
    ADD FOREIGN KEY (producer_id)
        REFERENCES producer (id);

COMMENT ON TABLE film_producer IS 'Producer`s Film';
COMMENT ON COLUMN film_producer.film_id IS 'Film ID';
COMMENT ON COLUMN film_producer.producer_id IS 'Producer ID';
