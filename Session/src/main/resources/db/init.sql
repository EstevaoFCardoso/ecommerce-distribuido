sql
Copiar código
-- Script para popular a base de dados H2

-- Limpar as tabelas existentes
DROP TABLE IF EXISTS SEAT_SESSION;
DROP TABLE IF EXISTS SESSIONN;
DROP TABLE IF EXISTS SEAT;
DROP TABLE IF EXISTS MOVIE;
DROP TABLE IF EXISTS CINE_ROOM;

-- Criar as tabelas
CREATE TABLE CINE_ROOM (
    ID_CINE_ROOM BIGINT AUTO_INCREMENT PRIMARY KEY,
    NUMBER_ROOM BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE MOVIE (
    ID_MOVIE BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    SYNOPSIS TEXT NOT NULL,
    DURATION BIGINT NOT NULL,
    GENRE VARCHAR(50) NOT NULL,
    CLASSIFICATION VARCHAR(50) NOT NULL
);

CREATE TABLE SEAT (
    ID_SEAT BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL
);

CREATE TABLE SESSIONN (
    ID_SESSION BIGINT AUTO_INCREMENT PRIMARY KEY,
    ID_MOVIE BIGINT,
    SESSION_DESCRIPTION VARCHAR(255),
    START_TIME TIMESTAMP,
    END_TIME TIMESTAMP,
    INIT_RANGE_TIME TIMESTAMP,
    END_RANGE_TIME TIMESTAMP,
    CINE_ROOM_ID BIGINT,
    FOREIGN KEY (ID_MOVIE) REFERENCES MOVIE(ID_MOVIE),
    FOREIGN KEY (CINE_ROOM_ID) REFERENCES CINE_ROOM(ID_CINE_ROOM)
);

CREATE TABLE SEAT_SESSION (
    ID_SEAT_SESSION BIGINT AUTO_INCREMENT PRIMARY KEY,
    SEAT_ID BIGINT,
    AVAILABLE_SEAT BIGINT NOT NULL,
    SESSION_ID BIGINT,
    FOREIGN KEY (SEAT_ID) REFERENCES SEAT(ID_SEAT),
    FOREIGN KEY (SESSION_ID) REFERENCES SESSIONN(ID_SESSION)
);

-- Inserir dados em CINE_ROOM
INSERT INTO CINE_ROOM (NUMBER_ROOM, NAME) VALUES
(1, 'Sala 1'),
(2, 'Sala 2'),
(3, 'Sala 3'),
(4, 'Sala 4'),
(5, 'Sala 5'),
(6, 'Sala 6'),
(7, 'Sala 7'),
(8, 'Sala 8'),
(9, 'Sala 9'),
(10, 'Sala 10'),
(11, 'Sala 11'),
(12, 'Sala 12'),
(13, 'Sala 13'),
(14, 'Sala 14'),
(15, 'Sala 15'),
(16, 'Sala 16'),
(17, 'Sala 17'),
(18, 'Sala 18'),
(19, 'Sala 19'),
(20, 'Sala 20');

-- Inserir dados em MOVIE
INSERT INTO MOVIE (TITLE, SYNOPSIS, DURATION, GENRE, CLASSIFICATION) VALUES
('Filme 1', 'Sinopse do filme 1', 120, 'Ação', '10 anos'),
('Filme 2', 'Sinopse do filme 2', 150, 'Drama', '12 anos'),
('Filme 3', 'Sinopse do filme 3', 90, 'Comédia', 'Livre'),
('Filme 4', 'Sinopse do filme 4', 110, 'Terror', '16 anos'),
('Filme 5', 'Sinopse do filme 5', 140, 'Ficção Científica', '14 anos'),
('Filme 6', 'Sinopse do filme 6', 100, 'Romance', '10 anos'),
('Filme 7', 'Sinopse do filme 7', 125, 'Aventura', '10 anos'),
('Filme 8', 'Sinopse do filme 8', 130, 'Animação', 'Livre'),
('Filme 9', 'Sinopse do filme 9', 95, 'Suspense', '12 anos'),
('Filme 10', 'Sinopse do filme 10', 135, 'Fantasia', '10 anos'),
('Filme 11', 'Sinopse do filme 11', 145, 'Ação', '12 anos'),
('Filme 12', 'Sinopse do filme 12', 100, 'Drama', '10 anos'),
('Filme 13', 'Sinopse do filme 13', 120, 'Comédia', 'Livre'),
('Filme 14', 'Sinopse do filme 14', 115, 'Terror', '16 anos'),
('Filme 15', 'Sinopse do filme 15', 125, 'Ficção Científica', '14 anos'),
('Filme 16', 'Sinopse do filme 16', 130, 'Romance', '10 anos'),
('Filme 17', 'Sinopse do filme 17', 105, 'Aventura', '10 anos'),
('Filme 18', 'Sinopse do filme 18', 130, 'Animação', 'Livre'),
('Filme 19', 'Sinopse do filme 19', 90, 'Suspense', '12 anos'),
('Filme 20', 'Sinopse do filme 20', 140, 'Fantasia', '10 anos');

-- Inserir dados em SEAT
INSERT INTO SEAT (NAME) VALUES
('Assento A1'),
('Assento A2'),
('Assento A3'),
('Assento A4'),
('Assento A5'),
('Assento B1'),
('Assento B2'),
('Assento B3'),
('Assento B4'),
('Assento B5'),
('Assento C1'),
('Assento C2'),
('Assento C3'),
('Assento C4'),
('Assento C5'),
('Assento D1'),
('Assento D2'),
('Assento D3'),
('Assento D4'),
('Assento D5');

-- Inserir dados em SESSIONN
INSERT INTO SESSIONN (ID_MOVIE, SESSION_DESCRIPTION, START_TIME, END_TIME, INIT_RANGE_TIME, END_RANGE_TIME, CINE_ROOM_ID) VALUES
(1, 'Sessão 1', '2024-10-29 10:00:00', '2024-10-29 12:00:00', '2024-10-29 09:00:00', '2024-10-29 09:30:00', 1),
(2, 'Sessão 2', '2024-10-29 12:00:00', '2024-10-29 14:00:00', '2024-10-29 11:00:00', '2024-10-29 11:30:00', 2),
(3, 'Sessão 3', '2024-10-29 14:00:00', '2024-10-29 16:00:00', '2024-10-29 13:00:00', '2024-10-29 13:30:00', 3),
(4, 'Sessão 4', '2024-10-29 16:00:00', '2024-10-29 18:00:00', '2024-10-29 15:00:00', '2024-10-29 15:30:00', 4),
(5, 'Sessão 5', '2024-10-29 18:00:00', '2024-10-29 20:00:00', '2024-10-29 17:00:00', '2024-10-29 17:30:00', 5),
(6, 'Sessão 6', '2024-10-29 20:00:00', '2024-10-29 22:00:00', '2024-10-29 19:00:00', '2024-10-29 19:30:00', 6),
(7, 'Sessão 7', '2024-10-30 10:00:00', '2024-10-30 12:00:00', '2024-10-30 09:00:00', '2024-10-30 09:30:00', 7),
(8, 'Sessão 8', '2024-10-30 12:00:00', '2024-10-30 14:00:00', '2024-10-30 11:00:00', '2024-10-30 11:30:00', 8),
(9, 'Sessão 9', '2024-10-30 14:00:00', '2024-10-30 16:00:00', '2024-10-30 13:00:00', '2024-10-30 13:30:00', 9),
(10, 'Sessão 10', '2024-10-30 16:00:00', '2024-10-30 18:00:00', '2024-10-30 15:00:00', '2024-10-30 15:30:00', 10),
(11, 'Sessão 11', '2024-10-30 18:00:00', '2024-10-30 20:00:00', '2024-10-30 17:00:00', '2024-10-30 17:30:00', 11),
(