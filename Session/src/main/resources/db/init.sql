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
