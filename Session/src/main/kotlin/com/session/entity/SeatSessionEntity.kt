package com.session.entity

import com.session.controller.api.request.dto.SeatSessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "seat_session")
class SeatSessionEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "seat_id")
    var seatId: Long? = null

    @Column(name = "available_seats")
    var availableSeats: Long? = null

    @ManyToOne
    @Column(name = "session_id")
    var sessionId: Long? = null
}

class SeatSessionAssembler {

    fun toEntity(seatSessionDTO: SeatSessionDTO): SeatSessionEntity {
        val seatSessionEntity = SeatSessionEntity()
        seatSessionEntity.seatId = seatSessionDTO.seatId
        seatSessionEntity.availableSeats = seatSessionDTO.availableSeats
        seatSessionEntity.sessionId = seatSessionDTO.sessionId ?: 0
        return seatSessionEntity
    }

    fun toDTO(seatSessionEntity: SeatSessionEntity): SeatSessionDTO {
        return SeatSessionDTO(
            availableSeats = seatSessionEntity.availableSeats?: 0L,
            sessionId = seatSessionEntity.sessionId ?: 0L,
            seatId = seatSessionEntity.seatId ?: 0
        )
    }
}