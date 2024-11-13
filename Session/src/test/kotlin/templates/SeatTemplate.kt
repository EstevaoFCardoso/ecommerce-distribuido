package templates

import com.session.entity.SeatEntity

class SeatTemplate {
    companion object{
        fun validSeatEntity(
            name: String = "A1"
        )= SeatEntity (
            name = name
        )
    }
}