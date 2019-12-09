CREATE TABLE producer (
    id     INT AUTO_INCREMENT NOT NULL,
    name   VARCHAR(100)
);

COMMENT ON TABLE producer IS 'Producers';
COMMENT ON COLUMN producer.id IS 'Producer ID';
COMMENT ON COLUMN producer.name IS 'Producer name';
