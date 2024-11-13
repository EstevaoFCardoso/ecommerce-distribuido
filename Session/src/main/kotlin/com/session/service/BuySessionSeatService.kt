package com.session.service

import com.session.controller.api.request.dto.BuySessionSeatDTO
import com.session.entity.BuySessionSeatAssembler
import com.session.repository.BuySessionSeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class BuySessionSeatService(
    private val buySessionSeatRepository: BuySessionSeatRepository,
    private val buySessionSeatAssembler: BuySessionSeatAssembler
) {
    fun createBuySessionSeat(dto: BuySessionSeatDTO): BuySessionSeatDTO {
        return buySessionSeatAssembler.toDTO(
            buySessionSeatRepository.save(buySessionSeatAssembler.toEntity(dto)))
    }

    fun getBuySessionSeatById(id: Long): BuySessionSeatDTO? {
        val entity = buySessionSeatRepository.findById(id)
        return entity.orElse(null)?.let { buySessionSeatAssembler.toDTO(it) }
    }

    fun deleteBuySessionSeat(id: Long): Boolean {
        if (!buySessionSeatRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        buySessionSeatRepository.deleteById(id)
        return true
    }
}