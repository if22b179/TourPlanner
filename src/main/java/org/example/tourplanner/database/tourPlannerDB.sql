CREATE TABLE IF NOT EXISTS tour (
    tour_id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    fromDest VARCHAR(255) NOT NULL,
    toDest VARCHAR(255) NOT NULL,
    transportType VARCHAR(255) NOT NULL,
    distance DOUBLE PRECISION NOT NULL,
    estimatedTime VARCHAR(255) NOT NULL,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tourlogs (
    tourlogs_id SERIAL PRIMARY KEY,
    dateTime VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    difficulty INT NOT NULL,
    totalDistance DOUBLE PRECISION NOT NULL,
    totalTime DOUBLE PRECISION NOT NULL,
    rating INT NOT NULL,
    tour_id INT NOT NULL,
    FOREIGN KEY (tour_id) REFERENCES tour(tour_id)  ON DELETE CASCADE
);

INSERT INTO tour (description, fromDest, toDest, transportType, distance, estimatedTime, image)
VALUES
    ('Wienerwald', 'a', 'b', 'bike', 20, '2 hours', NULL),
    ('Dopplerhütte', 'a', 'b', 'bike', 10, '2 hours', NULL),
    ('Dorfrunde', 'a', 'b', 'bike', 15, '2 hours', NULL);


