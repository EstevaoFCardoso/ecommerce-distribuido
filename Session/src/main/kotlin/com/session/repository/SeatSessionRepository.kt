package com.session.repository

import com.session.entity.SeatSessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatSessionRepository : JpaRepository<SeatSessionEntity, Long>