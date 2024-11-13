package com.session.repository

import com.session.entity.MovieEntity
import com.session.entity.SessionEntity
import com.session.enums.GenreEnum
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import templates.MovieTemplate
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@DataJpaTest
class SessionRepositoryTest {

    @Autowired
    private lateinit var sessionRepository: SessionRepository

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Test
    fun `test save and find session by ID`() {
        val movie =  MovieTemplate.validMovieEntity(
            title = "Inception",
            synopsis = "A mind-bending thriller",
            duration = 148L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )
        val savedMovie = movieRepository.save(movie)

        val session = SessionEntity(
            movie = savedMovie,
            description = "Evening session",
            startSession = LocalDateTime.now(),
            endSession = LocalDateTime.now().plusHours(2)
        )

        val savedSession = sessionRepository.save(session)
        val foundSession = sessionRepository.findById(savedSession.id)

        assertTrue(foundSession.isPresent)
        assertEquals("Evening session", foundSession.get().description)
        assertEquals(savedMovie.id, foundSession.get().movie?.id)
    }

    @Test
    fun `test find all sessions`() {
        val movie1 = MovieTemplate.validMovieEntity(
            title = "Avatar",
            synopsis = "An epic adventure",
            duration = 162L,
            genre = GenreEnum.FANTASY,
            classification = "PG-13"
        )
        val movie2 =  MovieTemplate.validMovieEntity(
            title = "Titanic",
            synopsis = "A tragic romance",
            duration = 195L,
            genre = GenreEnum.DRAMA,
            classification = "PG-13"
        )

        movieRepository.save(movie1)
        movieRepository.save(movie2)

        val session1 = SessionEntity(movie = movie1, description = "Session 1", startSession = LocalDateTime.now(), endSession = LocalDateTime.now().plusHours(2))
        val session2 = SessionEntity(movie = movie2, description = "Session 2", startSession = LocalDateTime.now(), endSession = LocalDateTime.now().plusHours(2))

        sessionRepository.save(session1)
        sessionRepository.save(session2)

        val sessions = sessionRepository.findAll()
        assertEquals(2, sessions.size)
    }

    @Test
    fun `test delete session`() {
        val movie =  MovieTemplate.validMovieEntity(
            title = "Interstellar",
            synopsis = "Space exploration beyond imagination",
            duration = 169L,
            genre = GenreEnum.SCIENCE_FICTION,
            classification = "PG-13"
        )
        val savedMovie = movieRepository.save(movie)

        val session = SessionEntity(movie = savedMovie, description = "Morning session", startSession = LocalDateTime.now(), endSession = LocalDateTime.now().plusHours(2))
        val savedSession = sessionRepository.save(session)

        savedSession.id.let { sessionRepository.deleteById(it) }

        val foundSession = savedSession.id.let { sessionRepository.findById(it) }
        assertFalse(foundSession.isPresent)
    }
}