package com.session.entity

import com.session.dto.SessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable
import java.time.LocalDateTime

@Getter
@Setter
@Entity
@Table(name = "session")
class SessionEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "movie_id")
    var idMovieEntity: Long? = null

    @Column(name = "start_time")
    var startSession: LocalDateTime? = null

    @Column(name = "end_time")
    var endSession: LocalDateTime? = null

    @Column(name = "init_range_time")
    var initRangeTime: LocalDateTime? = null

    @Column(name = "end_range_time")
    var endRangeTime: LocalDateTime? = null
}

fun SessionEntity.toDTO(): SessionDTO {
    return SessionDTO(
        movieId = this.idMovieEntity,
        startSession = this.startSession,
        endRangeTime = endRangeTime,
        initRangeTime = initRangeTime,
        endSession = endSession


    )
}