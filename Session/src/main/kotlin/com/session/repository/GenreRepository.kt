package com.session.repository

import com.session.enums.GenreEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : JpaRepository<GenreEnum, Long>