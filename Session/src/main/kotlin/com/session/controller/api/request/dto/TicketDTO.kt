package com.session.controller.api.request.dto

import java.util.UUID

data class TicketDTO(
    val id: Long = 0,
    val codTicket: UUID? = null,
    val ticketPrice: Double? = null
)