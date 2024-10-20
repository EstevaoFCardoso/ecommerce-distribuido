package com.session.entity

import com.session.controller.api.request.dto.SessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import org.springframework.stereotype.Component
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    var movieId: Long? = null

    @Column(name = "session_description")
    var description: String? = null

    @Column(name = "start_time")
    var startSession: LocalDateTime? = null

    @Column(name = "end_time")
    var endSession: LocalDateTime? = null

    @Column(name = "init_range_time")
    var initRangeTime: LocalDateTime? = null

    @Column(name = "end_range_time")
    var endRangeTime: LocalDateTime? = null
}

@Component
class SessionAssembler {

    fun toDTO(sessionEntity: SessionEntity): SessionDTO {
        return SessionDTO(
            movieId = sessionEntity.id,
            startSession = sessionEntity.startSession,
            endRangeTime = sessionEntity.endRangeTime,
            initRangeTime = sessionEntity.initRangeTime,
            endSession = sessionEntity.endSession,
            description = sessionEntity.description
        )
    }

    fun toEntity(sessionDTO: SessionDTO): SessionEntity {
        val sessionEntity = SessionEntity()
        sessionEntity.movieId = sessionDTO.movieId
        sessionEntity.startSession = sessionDTO.startSession
        sessionEntity.endSession = sessionDTO.endSession
        sessionEntity.initRangeTime = sessionDTO.initRangeTime
        sessionEntity.endRangeTime = sessionDTO.endRangeTime
        sessionEntity.description = sessionDTO.description
        return sessionEntity
    }
}
