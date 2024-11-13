package com.session.entity

import com.session.controller.api.request.dto.CineRoomDTO
import com.session.controller.api.request.dto.SessionDTO
import jakarta.persistence.*
import org.springframework.stereotype.Component

@Entity
@Table(name = "CINE_ROOM")
class CineRoomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CINE_ROOM")
    var id: Long = 0,

    @Column(name = "NUMBER_ROOM")
    var numberRoom: Long,

    @Column(name = "NAME")
    var name: String,

    @OneToMany(mappedBy = "cineRoom", cascade = [CascadeType.ALL])
    var sessions: List<SessionEntity> = mutableListOf()
)

@Component
class CineRoomAssembler(
    private val movieAssembler: MovieAssembler,
) {

    fun toDTO(cineRoomEntity: CineRoomEntity): CineRoomDTO {
        return CineRoomDTO(
            numberRoom = cineRoomEntity.numberRoom,
            name = cineRoomEntity.name,
            session = cineRoomEntity.sessions.let { listSessionsIterator ->
                listSessionsIterator.map { sessionIterator ->
                    SessionDTO(
                        movie = sessionIterator.movie?.let { movieIterator -> movieAssembler.toDTO(movieIterator) },
                        startSession = sessionIterator.startSession,
                        endSession = sessionIterator.endSession,
                        initRangeTime = sessionIterator.initRangeTime,
                        endRangeTime = sessionIterator.endRangeTime,
                        description = sessionIterator.description,
                    )
                }
            }
        )
    }

    fun toEntity(cineRoomDTO: CineRoomDTO): CineRoomEntity {
        return CineRoomEntity(
            numberRoom = cineRoomDTO.numberRoom ?: 0L,
            name = cineRoomDTO.name!!,
            sessions = cineRoomDTO.session.let { listSessionsIterator ->
                listSessionsIterator.map { sessionIterator ->
                    SessionEntity(
                        movie = sessionIterator.movie?.let { movieIterator -> movieAssembler.toEntity(movieIterator) },
                        startSession = sessionIterator.startSession,
                        endSession = sessionIterator.endSession,
                        initRangeTime = sessionIterator.initRangeTime,
                        endRangeTime = sessionIterator.endRangeTime,
                        description = sessionIterator.description,
                    )
                }
            }
        )
    }

}
