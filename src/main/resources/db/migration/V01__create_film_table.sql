CREATE TABLE film (
    id         INT AUTO_INCREMENT NOT NULL,
    year       INT,
    title      VARCHAR(100),
    studio     VARCHAR(100),
    producer   VARCHAR(200),
    winner     BOOLEAN
);

COMMENT ON TABLE film IS 'Film';
COMMENT ON COLUMN film.id IS 'Film ID';
COMMENT ON COLUMN film.year IS 'Film year';
COMMENT ON COLUMN film.title IS 'Film title';
COMMENT ON COLUMN film.studio IS 'Film studio';
COMMENT ON COLUMN film.producer IS 'Film producer';
COMMENT ON COLUMN film.winner IS 'Winner';