package com.session.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import java.util.*

class TicketEntity (
    @Id
    @Column(name = "ID_SESSION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "COD_TICKET")
    var codTicket: UUID? = null,

    @Column(name = "TICKET_PRICE")
    var ticketPrice: Double? = null
)

@Component
class TicketAssembler {
    fun toDTO(ticket: TicketEntity): TicketDTO {
        return TicketDTO(
            id = ticket.id,
            codTicket = ticket.codTicket,
            ticketPrice = ticket.ticketPrice
        )
    }

    fun toEntity(ticketDTO: TicketDTO): TicketEntity {
        return TicketEntity(
            codTicket = ticketDTO.codTicket,
            ticketPrice = ticketDTO.ticketPrice
        )
    }
}