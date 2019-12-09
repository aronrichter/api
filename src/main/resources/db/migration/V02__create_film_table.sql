CREATE TABLE film (
    id            INT AUTO_INCREMENT NOT NULL,
    year          INT,
    title         VARCHAR(100),
    studio        VARCHAR(100),
    producer_id   VARCHAR(200),
    winner        BOOLEAN
);

ALTER TABLE film
    ADD FOREIGN KEY (producer_id)
        REFERENCES producer(id);

COMMENT ON TABLE film IS 'Film';
COMMENT ON COLUMN film.id IS 'Film ID';
COMMENT ON COLUMN film.year IS 'Film year';
COMMENT ON COLUMN film.title IS 'Film title';
COMMENT ON COLUMN film.studio IS 'Film studio';
COMMENT ON COLUMN film.producer_id IS 'Film producer';
COMMENT ON COLUMN film.winner IS 'Winner';
