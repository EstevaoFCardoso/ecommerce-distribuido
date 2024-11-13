package com.session.repository

import com.session.entity.MovieEntity
import com.session.enums.GenreEnum
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@DataJpaTest
class MovieEntityTest {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Test
    fun `test save and find movie by ID`() {
        val movie = MovieEntity(
            title = "Inception",
            synopsis = "A mind-bending thriller",
            duration = 148L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        val savedMovie = movieRepository.save(movie)
        val foundMovie = movieRepository.findById(savedMovie.id!!)

        assertTrue(foundMovie.isPresent)
        assertEquals("Inception", foundMovie.get().title)
    }

    @Test
    fun `test find all movies`() {
        val movie1 = MovieEntity(
            title = "Avatar",
            synopsis = "An epic adventure",
            duration = 162L,
            genre = GenreEnum.FANTASY,
            classification = "PG-13"
        )
        val movie2 = MovieEntity(
            title = "Titanic",
            synopsis = "A tragic romance",
            duration = 195L,
            genre = GenreEnum.DRAMA,
            classification = "PG-13"
        )

        movieRepository.save(movie1)
        movieRepository.save(movie2)

        val movies = movieRepository.findAll()
        assertEquals(2, movies.size)
    }

    @Test
    fun `test delete movie`() {
        val movie = MovieEntity(
            title = "Interstellar",
            synopsis = "Space exploration beyond imagination",
            duration = 169L,
            genre = GenreEnum.SCIENCE_FICTION,
            classification = "PG-13"
        )

        val savedMovie = movieRepository.save(movie)
        savedMovie.id?.let { movieRepository.deleteById(it) }

        val foundMovie = savedMovie.id?.let { movieRepository.findById(it) }
        if (foundMovie != null) {
            assertFalse(foundMovie.isPresent)
        }
    }
}