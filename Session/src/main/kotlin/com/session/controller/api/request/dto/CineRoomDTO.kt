package com.session.controller.api.request.dto

import com.session.entity.CineRoomEntity

data class CineRoomDTO(
    val numberRoom: Long?,
    val name: String?,
    val sessionId: Long?
)