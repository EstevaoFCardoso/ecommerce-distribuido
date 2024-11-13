package com.session.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import java.util.*

class BuyEntity(

    @Id
    @Column(name = "ID_BUY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "COD_BUY")
    var codBuy: UUID? = null,

    @Column(name = "CPF")
    var cpf: String? = null,

    @Column(name = "PAYMENT_METHOD")
    var paymentMethod: String? = null
)

@Component
class BuyAssembler {
    fun toDTO(buy: BuyEntity): BuyDTO {
        return BuyDTO(
            id = buy.id,
            codBuy = buy.codBuy,
            cpf = buy.cpf,
            paymentMethod = buy.paymentMethod
        )
    }

    fun toEntity(buyDTO: BuyDTO): BuyEntity {
        return BuyEntity(
            codBuy = buyDTO.codBuy,
            cpf = buyDTO.cpf,
            paymentMethod = buyDTO.paymentMethod
        )
    }
}