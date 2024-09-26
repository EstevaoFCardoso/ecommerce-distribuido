package com.session.dto

import com.session.entity.CineRoomEntity

data class CineRoomDTO(
    val numberRoom: Long?,
    val name: String?
)

fun CineRoomDTO.toEntity(): CineRoomEntity {
    val cineRoomEntity = CineRoomEntity()
    cineRoomEntity.name = name?: ""
    cineRoomEntity.numberRoom= numberRoom?: 0L
    return cineRoomEntity
}