package templates

import com.session.entity.MovieEntity
import com.session.enums.GenreEnum

class MovieTemplate {
    companion object{
        fun validMovieEntity(
            title: String = "Inception",
            synopsis: String = "A mind-bending thriller",
            duration: Long = 148L,
            genre: GenreEnum = GenreEnum.ACTION,
            classification: String = "PG-13"
        )= MovieEntity (
            title = title,
            synopsis = synopsis,
            duration = duration,
            genre = genre,
            classification = classification
        )
    }
}