package com.session.dto

import com.session.entity.SessionEntity
import java.time.LocalDateTime

data class SessionDTO(
    val id: Long,
    val movieId: MovieDTO?,
    val startSession: LocalDateTime?,
    val endSession: LocalDateTime?,
    val initRangeTime: LocalDateTime?,
    val endRangeTime: LocalDateTime?
)

fun SessionDTO.toEntity(): SessionEntity {
    val sessionEntity = SessionEntity()
    sessionEntity.idMovieEntity = this.movieId?.toEntity()
    sessionEntity.startSession = this.startSession
    sessionEntity.endSession = this.endSession
    sessionEntity.initRangeTime = this.initRangeTime
    sessionEntity.endRangeTime = this.endRangeTime
    return sessionEntity
}