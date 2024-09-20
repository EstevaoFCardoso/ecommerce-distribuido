CREATE TABLE IF NOT EXISTS movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    synopsis VARCHAR(255),
    duration BIGINT,
    genre_id BIGINT,
    classification VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS genres (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS seats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    init_range_time TIMESTAMP,
    end_range_time TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE IF NOT EXISTS seat_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_id BIGINT,
    available_seats BIGINT,
    session_id BIGINT,
    FOREIGN KEY (seat_id) REFERENCES seats(id),
    FOREIGN KEY (session_id) REFERENCES sessions(id)
);

INSERT INTO genres (name) VALUES ('Action'), ('Comedy'), ('Drama');

INSERT INTO movies (title, synopsis, duration, genre_id, classification) VALUES
('Movie 1', 'Synopsis 1', 120, 1, 'PG-13'),
('Movie 2', 'Synopsis 2', 90, 2, 'R');

INSERT INTO seats (name) VALUES ('Seat 1'), ('Seat 2'), ('Seat 3');

INSERT INTO sessions (movie_id, start_time, end_time, init_range_time, end_range_time) VALUES
(1, '2023-09-01 14:00:00', '2023-09-01 16:00:00', '2023-09-01 13:00:00', '2023-09-01 14:00:00');

INSERT INTO seat_sessions (seat_id, available_seats, session_id) VALUES
(1, 50, 1),
(2, 50, 1),
(3, 50, 1);
