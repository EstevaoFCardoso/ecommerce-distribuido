package com.session.service

import com.session.controller.api.request.dto.SeatSessionDTO
import com.session.entity.SeatSessionAssembler
import com.session.repository.SeatSessionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SeatSessionService(
    private val seatSessionRepository: SeatSessionRepository,
    private val seatSessionAssembler: SeatSessionAssembler
) {

    fun getAllSeatSessions(): List<SeatSessionDTO> {
        return seatSessionRepository.findAll().map { seatSession ->
            seatSessionAssembler.toDTO(seatSession)
        }
    }

    fun getSeatSessionById(id: Long): SeatSessionDTO? {
        val seatSession = seatSessionRepository.findById(id).orElseThrow {
            EntityNotFoundException("SeatSession with ID $id not found")
        }
        return seatSessionAssembler.toDTO(seatSession)
    }

    fun createSeatSession(seatSessionDTO: SeatSessionDTO): SeatSessionDTO {
        return seatSessionAssembler.toDTO(seatSessionRepository.save(seatSessionAssembler.toEntity(seatSessionDTO))) }

    fun updateSeatSession(id: Long, seatSessionDTO: SeatSessionDTO) : SeatSessionDTO {
        if (!seatSessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return seatSessionAssembler.toDTO(seatSessionRepository.save(seatSessionAssembler.toEntity(seatSessionDTO)))
    }

    fun deleteSeatSession(id: Long): Boolean{
        if (!seatSessionRepository.existsById(id)) {
            throw EntityNotFoundException("SeatSession with ID $id not found")
        }
        seatSessionRepository.deleteById(id)
        return true
    }
}