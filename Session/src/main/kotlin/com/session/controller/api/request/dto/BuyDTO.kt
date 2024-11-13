package com.session.controller.api.request.dto

import java.util.UUID

data class BuyDTO(
    val id: Long = 0,
    val codBuy: UUID? = null,
    val cpf: String? = null,
    val paymentMethod: String? = null
)