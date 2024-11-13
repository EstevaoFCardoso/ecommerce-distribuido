package templates

import com.session.entity.CineRoomEntity
import com.session.entity.MovieEntity
import com.session.enums.GenreEnum

class CineRoomTemplate {
    companion object{
        fun validCineRoomEntity(
            numberRoom: Long = 1,
            name: String = "Main Theater"
        )= CineRoomEntity (
            numberRoom = numberRoom,
            name = name
        )
    }
}