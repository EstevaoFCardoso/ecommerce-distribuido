package com.session.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "seat_session")
class SeatSessionEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "movie_id")
    var seat_id: SeatEntity? = null

    @Column(name = "available_seats")
    var availableSeats: Long? = null

    @Column(name = "session_id")
    var sessionId: SessionEntity? = null
}