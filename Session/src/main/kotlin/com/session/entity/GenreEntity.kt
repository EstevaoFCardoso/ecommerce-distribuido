package com.session.entity

import com.session.dto.GenreDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "session")
class GenreEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "name")
    var name: String? = null
}
fun GenreEntity.toDTO(): GenreDTO {
    return GenreDTO(
        id = id,
        name= name
    )
}