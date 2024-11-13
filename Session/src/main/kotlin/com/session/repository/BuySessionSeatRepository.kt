package com.session.repository

import com.session.entity.BuySessionSeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuySessionSeatRepository : JpaRepository<BuySessionSeatEntity, Long>