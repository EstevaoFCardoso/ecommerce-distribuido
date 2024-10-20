package com.session.controller.api.request.dto

data class SeatSessionDTO(
    val seatId: Long?,
    val availableSeats: Long?,
    val sessionId: Long?
)