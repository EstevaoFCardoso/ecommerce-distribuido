package com.session.repository

import com.session.entity.CineRoomEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@DataJpaTest
class CineRoomRepositoryTest {

    @Autowired
    private lateinit var cineRoomRepository: CineRoomRepository

    @Test
    fun `test save and find cine room by ID`() {
        val cineRoom = CineRoomEntity(
            numberRoom = 1,
            name = "Main Theater"
        )

        val savedCineRoom = cineRoomRepository.save(cineRoom)
        val foundCineRoom = cineRoomRepository.findById(savedCineRoom.id)

        assertTrue(foundCineRoom.isPresent)
        assertEquals("Main Theater", foundCineRoom.get().name)
    }

    @Test
    fun `test delete cine room`() {
        val cineRoom = CineRoomEntity(
            numberRoom = 2,
            name = "VIP Room"
        )

        val savedCineRoom = cineRoomRepository.save(cineRoom)
        cineRoomRepository.deleteById(savedCineRoom.id)

        val foundCineRoom = cineRoomRepository.findById(savedCineRoom.id)
        assertFalse(foundCineRoom.isPresent)
    }

    @Test
    fun `test find all cine rooms`() {
        val cineRoom1 = CineRoomEntity(
            numberRoom = 3,
            name = "Room 3"
        )
        val cineRoom2 = CineRoomEntity(
            numberRoom = 4,
            name = "Room 4"
        )

        cineRoomRepository.save(cineRoom1)
        cineRoomRepository.save(cineRoom2)

        val cineRooms = cineRoomRepository.findAll()
        assertEquals(2, cineRooms.size)
    }
}