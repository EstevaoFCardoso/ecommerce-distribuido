package com.session.service

import com.session.controller.api.request.dto.MovieDTO
import com.session.entity.MovieAssembler
import com.session.repository.MovieRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val assembler: MovieAssembler
) {

    fun createMovie(movie: MovieDTO): MovieDTO {
        return assembler.toDTO(movieRepository.save(assembler.toEntity(movie)))
    }

    fun getById(id: Long): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow {
            EntityNotFoundException("Movie with ID $id not found")
        }
        return assembler.toDTO(movie)
    }

    fun listMovies(): List<MovieDTO> {
        return movieRepository.findAll().map { movie ->
            assembler.toDTO(movie)
        }
    }

    fun updateMovie(id: Long, movie: MovieDTO): MovieDTO {
        if (!movieRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return assembler.toDTO(movieRepository.save(assembler.toEntity(movie)))
    }

    fun deleteMovie(id: Long): Boolean {
        if (!movieRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        movieRepository.deleteById(id)
        return true
    }
}