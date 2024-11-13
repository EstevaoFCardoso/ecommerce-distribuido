package com.session.entity

import com.session.controller.api.request.dto.SeatSessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import org.springframework.stereotype.Component

@Getter
@Setter
@Entity
@Table(name = "SEAT_SESSION")
class SeatSessionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEAT_SESSION")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    val seat: SeatEntity,

    @Column(name = "AVAILABLE_SEAT")
    val availableSeats: Long,

    @ManyToOne
    @JoinColumn(name = "SESSION_ID")
    val session: SessionEntity
)

@Component
class SeatSessionAssembler(
    private val seatAssembler: SeatAssembler,
    private val sessionAssembler: SessionAssembler
) {

    fun toEntity(seatSessionDTO: SeatSessionDTO): SeatSessionEntity {
        return SeatSessionEntity(
            seat = seatAssembler.toEntity(seatSessionDTO.seat),
            availableSeats = seatSessionDTO.availableSeats,
            session = sessionAssembler.toEntity(seatSessionDTO.session),
        )
    }

    fun toDTO(seatSessionEntity: SeatSessionEntity): SeatSessionDTO {
        return SeatSessionDTO(
            seat = seatAssembler.toDTO(seatSessionEntity.seat),
            availableSeats = seatSessionEntity.availableSeats,
            session = sessionAssembler.toDTO(seatSessionEntity.session),
        )
    }
}