package com.session.controller

import com.session.controller.api.CineRoomController
import com.session.controller.api.request.dto.CineRoomDTO
import com.session.service.CineRoomService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExtendWith(MockitoExtension::class)
class CineRoomControllerTest {


    @Mock
    private lateinit var cineRoomService: CineRoomService

    @InjectMocks
    private lateinit var cineRoomController: CineRoomController

    @Test
    fun `test get all cine rooms`() {
        val cineRoomList = listOf(
            CineRoomDTO(numberRoom = 1, name = "Room 1", session = listOf()),
            CineRoomDTO(numberRoom = 2, name = "Room 2", session = listOf())
        )

        `when`(cineRoomService.getAllCineRooms()).thenReturn(cineRoomList)

        val response: ResponseEntity<List<CineRoomDTO>> = cineRoomController.getAllCineRooms()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(2, response.body!!.size)
        assertEquals("Room 1", response.body!![0].name)
    }

    @Test
    fun `test get cine room by ID`() {
        val cineRoomDTO = CineRoomDTO(numberRoom = 1, name = "Room 1", session = listOf())

        `when`(cineRoomService.getCineRoomById(1L)).thenReturn(cineRoomDTO)

        val response: ResponseEntity<CineRoomDTO> = cineRoomController.getCineRoomById(1L)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(cineRoomDTO, response.body)
    }

    @Test
    fun `test get cine room by ID not found`() {
        `when`(cineRoomService.getCineRoomById(1L)).thenReturn(null)

        val response: ResponseEntity<CineRoomDTO> = cineRoomController.getCineRoomById(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
    }

    @Test
    fun `test create cine room`() {
        val cineRoomDTO = CineRoomDTO(numberRoom = 3, name = "Room 3", session = listOf())

        `when`(cineRoomService.createCineRoom(cineRoomDTO)).thenReturn(cineRoomDTO)

        val response: ResponseEntity<CineRoomDTO> = cineRoomController.createCineRoom(cineRoomDTO)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(cineRoomDTO, response.body)
    }

    @Test
    fun `test update cine room`() {
        val cineRoomDTO = CineRoomDTO(numberRoom = 1, name = "Updated Room", session = listOf())

        `when`(cineRoomService.updateCineRoom(1L, cineRoomDTO)).thenReturn(cineRoomDTO)

        val response: ResponseEntity<CineRoomDTO> = cineRoomController.updateCineRoom(1L, cineRoomDTO)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(cineRoomDTO, response.body)
    }

    @Test
    fun `test delete cine room`() {
        `when`(cineRoomService.deleteCineRoom(1L)).thenReturn(true)

        val response: ResponseEntity<Void> = cineRoomController.deleteCineRoom(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `test delete cine room not found`() {
        `when`(cineRoomService.deleteCineRoom(1L)).thenReturn(false)

        val response: ResponseEntity<Void> = cineRoomController.deleteCineRoom(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}