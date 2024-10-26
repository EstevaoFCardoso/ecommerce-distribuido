package com.session.controller.api.request.dto

data class CineRoomDTO(
    val numberRoom: Long?,
    val name: String?,
    val session: List<SessionDTO>
)