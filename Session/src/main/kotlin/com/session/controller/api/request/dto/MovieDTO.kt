package com.session.controller.api.request.dto

import com.session.entity.MovieEntity
import com.session.enums.GenreEnum

data class MovieDTO(
    val title: String?,
    val synopsis: String?,
    val duration: Long?,
    val genre: String?,
    val classification: String?
)