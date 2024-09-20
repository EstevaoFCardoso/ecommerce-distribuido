package com.session.repository

import com.session.entity.CineRoomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CineRoomRepository : JpaRepository<CineRoomEntity, Long>