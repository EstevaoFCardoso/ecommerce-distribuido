package com.session.entity

import com.session.controller.api.request.dto.MovieDTO
import com.session.enums.GenreEnum
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "movies")
class MovieEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "title")
    var title: String? = null

    @Column(name = "synopsis")
    var synopsis: String? = null

    @Column(name = "duration")
    var duration: Long? = null

    @Column(name = "genre")
    var genre: String? = null

    @Column(name = "classification")
    var classification: String? = null

}

class MovieAssembler {

    fun toDTO(movie: MovieEntity): MovieDTO {
        return MovieDTO(
            title = movie.title ?: "",
            synopsis = movie.synopsis ?: "",
            duration = movie.duration ?: 0L,
            genre = GenreEnum.fromName(movie.genre.toString()).toString(),
            classification = movie.classification ?: ""
        )
    }

    fun toEntity(movie: MovieDTO): MovieEntity {
        val movieEntity = MovieEntity()
        movieEntity.title = movie.title
        movieEntity.synopsis = movie.synopsis
        movieEntity.duration = movie.duration
        movieEntity.genre = GenreEnum.fromName(movie.genre.toString()).toString()
        movieEntity.classification = movie.classification
        return movieEntity
    }

}
