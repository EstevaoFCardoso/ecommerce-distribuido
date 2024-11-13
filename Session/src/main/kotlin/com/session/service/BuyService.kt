package com.session.service

import com.session.controller.api.request.dto.BuyDTO
import com.session.entity.BuyAssembler
import com.session.repository.BuyRepository
import org.springframework.stereotype.Service

@Service
class BuyService(
    private val buyRepository: BuyRepository,
    private val buyAssembler: BuyAssembler
) {
    fun createBuy(buyDTO: BuyDTO): BuyDTO {
        val buyEntity = buyAssembler.toEntity(buyDTO)
        val savedBuy = buyRepository.save(buyEntity)
        return buyAssembler.toDTO(savedBuy)
    }

    fun getBuyById(id: Long): BuyDTO? {
        val buy = buyRepository.findById(id)
        return buy.orElse(null)?.let { buyAssembler.toDTO(it) }
    }

    fun updateBuy(id: Long, buyDTO: BuyDTO): BuyDTO? {
        return buyRepository.findById(id).map {
            val updatedBuy = it.apply {
                codBuy = buyDTO.codBuy
                cpf = buyDTO.cpf
                paymentMethod = buyDTO.paymentMethod
            }
            buyRepository.save(updatedBuy)
            buyAssembler.toDTO(updatedBuy)
        }.orElse(null)
    }

    fun deleteBuy(id: Long) {
        buyRepository.deleteById(id)
    }
}