package com.session.service

import com.session.controller.api.request.dto.SeatDTO
import com.session.entity.SeatAssembler
import com.session.repository.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SeatService(
    private val seatRepository: SeatRepository,
    private val seatAssembler: SeatAssembler
) {

    fun getAllSeats(): List<SeatDTO> {
        return seatRepository.findAll().map { seat ->
            seatAssembler.toDTO(seat)
        }
    }

    fun getSeatById(id: Long): SeatDTO? {
        val seat = seatRepository.findById(id).orElseThrow {
            EntityNotFoundException("Seat with ID $id not found")
        }
        return seatAssembler.toDTO(seat)
    }

    fun createSeat(seat: SeatDTO): SeatDTO {
        return seatAssembler.toDTO(seatRepository.save(seatAssembler.toEntity(seat)))
    }

    fun updateSeat(id: Long, seat: SeatDTO): SeatDTO? {
        return if (seatRepository.existsById(id)) {
            seatAssembler.toDTO(seatRepository.save(seatAssembler.toEntity(seat)))
        } else {
            null
        }
    }

    fun deleteSeat(id: Long): Boolean {
        if (!seatRepository.existsById(id)) {
            throw EntityNotFoundException("Seat with ID $id not found")
        }
        seatRepository.deleteById(id)
        return true
    }
}