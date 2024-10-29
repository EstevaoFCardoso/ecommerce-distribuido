package com.session.controller

import com.session.controller.api.SeatController
import com.session.controller.api.request.dto.SeatDTO
import com.session.service.SeatService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class SeatControllerTest {

    private lateinit var seatService: SeatService
    private lateinit var seatController: SeatController

    @BeforeEach
    fun setUp() {
        seatService = mock(SeatService::class.java)
        seatController = SeatController(seatService)
    }

    @Test
    fun `should return all seats`() {
        val seats = listOf(SeatDTO("A1"), SeatDTO("A2"))
        `when`(seatService.getAllSeats()).thenReturn(seats)

        val response: ResponseEntity<List<SeatDTO>> = seatController.getAllSeats()

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == seats)
    }

    @Test
    fun `should return seat by ID`() {
        val seat = SeatDTO("A1")
        val seatId = 1L
        `when`(seatService.getSeatById(seatId)).thenReturn(seat)

        val response: ResponseEntity<SeatDTO> = seatController.getSeatById(seatId)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == seat)
    }

    @Test
    fun `should return NOT_FOUND for nonexistent seat`() {
        val seatId = 2L
        `when`(seatService.getSeatById(seatId)).thenReturn(null)

        val response: ResponseEntity<SeatDTO> = seatController.getSeatById(seatId)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `should create a new seat`() {
        val seatDTO = SeatDTO("A1")
        val createdSeat = SeatDTO("A1")
        `when`(seatService.createSeat(seatDTO)).thenReturn(createdSeat)

        val response: ResponseEntity<SeatDTO> = seatController.createSeat(seatDTO)

        assert(response.statusCode == HttpStatus.CREATED)
        assert(response.body == createdSeat)
    }

    @Test
    fun `should update an existing seat`() {
        val seatId = 1L
        val updatedSeat = SeatDTO("A1")
        `when`(seatService.updateSeat(seatId, updatedSeat)).thenReturn(updatedSeat)

        val response: ResponseEntity<SeatDTO> = seatController.updateSeat(seatId, updatedSeat)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == updatedSeat)
    }

    @Test
    fun `should delete a seat successfully`() {
        val seatId = 1L
        `when`(seatService.deleteSeat(seatId)).thenReturn(true)

        val response: ResponseEntity<Void> = seatController.deleteSeat(seatId)

        assert(response.statusCode == HttpStatus.NO_CONTENT)
    }

    @Test
    fun `should return NOT_FOUND when deleting a nonexistent seat`() {
        val seatId = 2L
        `when`(seatService.deleteSeat(seatId)).thenReturn(false)

        val response: ResponseEntity<Void> = seatController.deleteSeat(seatId)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }
}