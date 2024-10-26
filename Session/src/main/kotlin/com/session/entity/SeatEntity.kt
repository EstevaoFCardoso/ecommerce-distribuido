package com.session.entity

import com.session.controller.api.request.dto.SeatDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "seat")
class SeatEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEAT")
    var id: Long = 0,

    @Column(name = "NAME")
    var name: String? = null
)

class SeatAssembler {
    fun toDTO(seat: SeatEntity): SeatDTO {
        return SeatDTO(
            name = seat.name
        )
    }

    fun toEntity(seat: SeatDTO): SeatEntity {
        val seatEntity = SeatEntity()
        seatEntity.name = seat.name
        return seatEntity
    }
}
