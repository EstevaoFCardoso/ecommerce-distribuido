package com.session.service

import com.session.controller.api.request.dto.TicketDTO
import com.session.entity.TicketAssembler
import com.session.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val ticketAssembler: TicketAssembler
) {
    fun createTicket(ticketDTO: TicketDTO): TicketDTO {
        val ticketEntity = ticketAssembler.toEntity(ticketDTO)
        val savedTicket = ticketRepository.save(ticketEntity)
        return ticketAssembler.toDTO(savedTicket)
    }

    fun getTicketById(id: Long): TicketDTO? {
        val ticket = ticketRepository.findById(id)
        return ticket.orElse(null)?.let { ticketAssembler.toDTO(it) }
    }

    fun updateTicket(id: Long, ticketDTO: TicketDTO): TicketDTO? {
        return ticketRepository.findById(id).map {
            val updatedTicket = it.apply {
                codTicket = ticketDTO.codTicket
                ticketPrice = ticketDTO.ticketPrice
            }
            ticketRepository.save(updatedTicket)
            ticketAssembler.toDTO(updatedTicket)
        }.orElse(null)
    }

    fun deleteTicket(id: Long) {
        ticketRepository.deleteById(id)
    }
}