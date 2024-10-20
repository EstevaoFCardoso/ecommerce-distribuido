package com.session.service

import com.session.enums.GenreEnum
import com.session.repository.GenreRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class GenreService(
    private val genreRepository: GenreRepository
) {

    fun getAllGenres(): List<String> {
        return genreRepository.findAll().map { genre ->
            genre.toString()
        }
    }

    fun getGenreById(id: Long): String? {
        val genre = genreRepository.findById(id).orElseThrow {
            EntityNotFoundException("Genre with ID $id not found")
        }
        return genre.toString()
    }

    fun createGenre(genre: GenreEnum):  String{
        return genreRepository.save(genre).toString()
    }

    fun updateGenre(id: Long, genre: GenreEnum): String? {
        if (!genreRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return genreRepository.save(genre).toString()
    }

    fun deleteGenre(id: Long) : Boolean {
        if (!genreRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        genreRepository.deleteById(id)
        return true
    }
}