package com.session.dto

import com.session.entity.MovieEntity

data class MovieDTO(
    val title: String,
    val synopsis: String,
    val duration: Long,
    val gender: String,
    val classification: String
)
fun MovieDTO.toEntity(): MovieEntity {
    val movieEntity = MovieEntity()
    movieEntity.title = this.title
    movieEntity.synopsis = this.synopsis
    movieEntity.duration = this.duration
    movieEntity.gender = this.gender
    movieEntity.classification = this.classification
    return movieEntity
}