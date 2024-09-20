package com.session.service

import com.session.entity.SeatSessionEntity
import com.session.repository.SeatSessionRepository
import org.springframework.stereotype.Service

@Service
class SeatSessionService(
    private val seatSessionRepository: SeatSessionRepository
) {

    fun getAllSeatSessions(): List<SeatSessionEntity> {
        return seatSessionRepository.findAll()
    }

    fun getSeatSessionById(id: Long): SeatSessionEntity? {
        return seatSessionRepository.findById(id).orElse(null)
    }

    fun createSeatSession(seatSessionEntity: SeatSessionEntity): SeatSessionEntity {
        return seatSessionRepository.save(seatSessionEntity)
    }

    fun updateSeatSession(id: Long, seatSessionEntity: SeatSessionEntity): SeatSessionEntity? {
        return if (seatSessionRepository.existsById(id)) {
            seatSessionRepository.save(seatSessionEntity)
        } else {
            null
        }
    }

    fun deleteSeatSession(id: Long) {
        if (seatSessionRepository.existsById(id)) {
            seatSessionRepository.deleteById(id)
        }
    }
}