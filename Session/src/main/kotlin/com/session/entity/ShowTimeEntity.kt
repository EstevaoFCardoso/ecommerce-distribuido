package com.session.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable
import java.time.LocalDateTime

@Getter
@Setter
@Entity
@Table(name = "session_time")
class ShowTimeEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private val idMovieEntity: MovieEntity? = null

    @Column(name = "start_time")
    private val startSession: LocalDateTime? = null

    @Column(name = "end_time")
    private val endSession: LocalDateTime? = null

}