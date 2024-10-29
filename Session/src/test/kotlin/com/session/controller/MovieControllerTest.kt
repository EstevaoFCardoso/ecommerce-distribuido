package com.session.controller

import com.session.controller.api.MovieController
import com.session.controller.api.error.ResourceNotFoundException
import com.session.controller.api.request.dto.MovieDTO
import com.session.controller.api.response.ListMoviesResponse
import com.session.enums.GenreEnum
import com.session.service.MovieService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.*

@ExtendWith(MockitoExtension::class)
class MovieControllerTest {

    @Mock
    private lateinit var movieService: MovieService

    @InjectMocks
    private lateinit var movieController: MovieController

    @Test
    fun `test create movie`() {
        val movieDTO = MovieDTO(
            title = "Movie Title",
            synopsis = "Synopsis",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        `when`(movieService.createMovie(movieDTO)).thenReturn(movieDTO)

        val response: ResponseEntity<MovieDTO> = movieController.create(movieDTO)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(movieDTO, response.body)
    }

    @Test
    fun `test get movie by ID`() {
        val movieDTO = MovieDTO(
            title = "Movie Title",
            synopsis = "Synopsis",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        `when`(movieService.getById(1L)).thenReturn(movieDTO)

        val response: ResponseEntity<MovieDTO> = movieController.getMovieById(1L)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(movieDTO, response.body)
    }

    @Test
    fun `test list movies`() {
        val movieList = listOf(
            MovieDTO("Movie1", "Synopsis1", 100L, GenreEnum.ACTION, "PG"),
            MovieDTO("Movie2", "Synopsis2", 120L, GenreEnum.COMEDY, "PG-13")
        )

        `when`(movieService.listMovies()).thenReturn(movieList)

        val response: ResponseEntity<List<ListMoviesResponse>> = movieController.listMovies()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(2, response.body!!.size)
        assertEquals("Movie1", response.body!![0].title)
        assertEquals("Movie2", response.body!![1].title)
    }

    @Test
    fun `test update movie`() {
        val movieDTO = MovieDTO(
            title = "Updated Title",
            synopsis = "Updated Synopsis",
            duration = 130L,
            genre = GenreEnum.DRAMA,
            classification = "R"
        )

        `when`(movieService.updateMovie(1L, movieDTO)).thenReturn(movieDTO)

        val response: ResponseEntity<MovieDTO> = movieController.updateMovie(1L, movieDTO)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(movieDTO, response.body)
    }

    @Test
    fun `test delete movie`() {
        `when`(movieService.deleteMovie(1L)).thenReturn(true)

        val response: ResponseEntity<Void> = movieController.delete(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `test delete movie throws ResourceNotFoundException`() {
        `when`(movieService.deleteMovie(1L)).thenReturn(false)

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            movieController.delete(1L)
        }

        assertEquals("Recurso com ID 1 não foi encontrado.", exception.message)
    }
}