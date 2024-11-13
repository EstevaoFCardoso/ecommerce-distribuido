package com.session.repository

import com.session.entity.BuyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyRepository : JpaRepository<BuyEntity, Long>