package com.session.entity

import com.session.controller.api.request.dto.SessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Getter
@Setter
@Entity
@Table(name = "SESSION")
class SessionEntity(
    @Id
    @Column(name = "ID_SESSION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "ID_MOVIE")
    var movie: MovieEntity? = null,

    @Column(name = "SESSION_DESCRIPTION")
    var description: String? = null,

    @Column(name = "START_TIME")
    var startSession: LocalDateTime? = null,

    @Column(name = "END_TIME")
    var endSession: LocalDateTime? = null,

    @Column(name = "INIT_RANGE_TIME")
    var initRangeTime: LocalDateTime? = null,

    @Column(name = "END_RANGE_TIME")
    var endRangeTime: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "CINE_ROOM_ID")
    var cineRoom: CineRoomEntity? = null,
)

@Component
class SessionAssembler(
    private val assembler: MovieAssembler,
) {

    fun toDTO(sessionEntity: SessionEntity): SessionDTO {
        return SessionDTO(
            movie = sessionEntity.movie?.let { assembler.toDTO(it) },
            startSession = sessionEntity.startSession,
            endRangeTime = sessionEntity.endRangeTime,
            initRangeTime = sessionEntity.initRangeTime,
            endSession = sessionEntity.endSession,
            description = sessionEntity.description,
        )
    }

    fun toEntity(sessionDTO: SessionDTO): SessionEntity {
        return SessionEntity(
            movie = sessionDTO.movie?.let { assembler.toEntity(it) },
            startSession = sessionDTO.startSession,
            endSession = sessionDTO.endSession,
            initRangeTime = sessionDTO.initRangeTime,
            endRangeTime = sessionDTO.endRangeTime,
            description = sessionDTO.description,
        )
    }
}
