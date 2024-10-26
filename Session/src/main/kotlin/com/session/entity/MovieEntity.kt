package com.session.entity

import com.session.controller.api.request.dto.MovieDTO
import com.session.enums.GenreEnum
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Entity
@Table(name = "movies")
class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIE")
    val id: Long? = null,

    @Column(name = "TITLE")
    val title: String,

    @Column(name = "SYNOPSIS")
    val synopsis: String,

    @Column(name = "DURATION")
    val duration: Long,

    @Column(name = "GENRE")
    val genre: GenreEnum,

    @Column(name = "CLASSIFICATION")
    val classification: String,

    )

class MovieAssembler {

    fun toDTO(movie: MovieEntity): MovieDTO {
        return MovieDTO(
            title = movie.title,
            synopsis = movie.synopsis,
            duration = movie.duration,
            genre = movie.genre,
            classification = movie.classification
        )
    }

    fun toEntity(movie: MovieDTO): MovieEntity {
        return MovieEntity(
            title = movie.title,
            synopsis = movie.synopsis,
            duration = movie.duration,
            genre = movie.genre,
            classification = movie.classification
        )
    }

}
