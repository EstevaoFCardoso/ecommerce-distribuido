package com.session.dto

import com.session.entity.SeatEntity
import com.session.entity.SeatSessionEntity
import com.session.entity.SessionEntity

data class SeatSessionDTO(
    val id: Long,
    val seatId: Long?,
    val availableSeats: Long?,
    val sessionId: Long?
)

fun SeatSessionDTO.toEntity(): SeatSessionEntity {
    val seatSessionEntity = SeatSessionEntity()
    seatSessionEntity.id = this.id
    seatSessionEntity.seat_id = SeatEntity().apply { id = this@toEntity.seatId ?: 0 }
    seatSessionEntity.availableSeats = this.availableSeats
    seatSessionEntity.sessionId = SessionEntity().apply { id = this@toEntity.sessionId ?: 0 }
    return seatSessionEntity
}