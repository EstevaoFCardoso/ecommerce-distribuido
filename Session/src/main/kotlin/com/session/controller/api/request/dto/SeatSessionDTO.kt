package com.session.controller.api.request.dto

data class SeatSessionDTO(
    val seat: SeatDTO,
    val availableSeats: Long,
    val session: SessionDTO
)