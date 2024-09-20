package com.session.service

import com.session.entity.SeatEntity
import com.session.repository.SeatRepository
import org.springframework.stereotype.Service

@Service
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun getAllSeats(): List<SeatEntity> {
        return seatRepository.findAll()
    }

    fun getSeatById(id: Long): SeatEntity? {
        return seatRepository.findById(id).orElse(null)
    }

    fun createSeat(seatEntity: SeatEntity): SeatEntity {
        return seatRepository.save(seatEntity)
    }

    fun updateSeat(id: Long, seatEntity: SeatEntity): SeatEntity? {
        return if (seatRepository.existsById(id)) {
            seatEntity.id = id
            seatRepository.save(seatEntity)
        } else {
            null
        }
    }

    fun deleteSeat(id: Long) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id)
        }
    }
}