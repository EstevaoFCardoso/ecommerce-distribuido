package com.session.controller.api.request.dto

import java.time.LocalDateTime

data class SessionDTO(
    val movieId: Long?,
    val startSession: LocalDateTime?,
    val endSession: LocalDateTime?,
    val initRangeTime: LocalDateTime?,
    val endRangeTime: LocalDateTime?,
    var description: String? = null
)