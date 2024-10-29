package com.session.controller.api.request.dto

import java.time.LocalDateTime

data class SessionDTO(
    var movie: MovieDTO?,
    var startSession: LocalDateTime?,
    var endSession: LocalDateTime?,
    var initRangeTime: LocalDateTime?,
    var endRangeTime: LocalDateTime?,
    var description: String? = null
    )