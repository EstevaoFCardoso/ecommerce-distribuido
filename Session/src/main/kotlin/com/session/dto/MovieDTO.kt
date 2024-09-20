package com.session.dto

import com.session.entity.GenreEntity
import com.session.entity.MovieEntity

data class MovieDTO(
    val title: String?,
    val synopsis: String?,
    val duration: Long?,
    val genre: GenreDTO?,
    val classification: String?
)

data class GenreDTO(
    val id: Long,
    val name: String?
)

fun MovieDTO.toEntity(): MovieEntity {
    val movieEntity = MovieEntity()
    movieEntity.title = this.title
    movieEntity.synopsis = this.synopsis
    movieEntity.duration = this.duration
    movieEntity.genreId = this.genre?.toEntity()
    movieEntity.classification = this.classification
    return movieEntity
}

fun GenreDTO.toEntity(): GenreEntity {
    val genreEntity = GenreEntity()
    genreEntity.id = this.id
    genreEntity.name = this.name
    return genreEntity
}