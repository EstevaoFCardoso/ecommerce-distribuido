package com.session.service

import com.session.entity.GenreEntity
import com.session.repository.GenreRepository
import org.springframework.stereotype.Service

@Service
class GenreService(
    private val genreRepository: GenreRepository
) {

    fun getAllGenres(): List<GenreEntity> {
        return genreRepository.findAll()
    }

    fun getGenreById(id: Long): GenreEntity? {
        return genreRepository.findById(id).orElse(null)
    }

    fun createGenre(genreEntity: GenreEntity): GenreEntity {
        return genreRepository.save(genreEntity)
    }

    fun updateGenre(id: Long, genreEntity: GenreEntity): GenreEntity? {
        return if (genreRepository.existsById(id)) {
            genreEntity.id = id
            genreRepository.save(genreEntity)
        } else {
            null
        }
    }

    fun deleteGenre(id: Long) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id)
        }
    }
}