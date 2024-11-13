package com.session.controller.api.request.dto

data class BuySessionSeatDTO(
    val seat: SeatDTO,
    val session: SessionDTO,
    val ticket: TicketDTO,
    val buy: BuyDTO,
    val pricePaid: Double
)