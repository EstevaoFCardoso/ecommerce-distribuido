package com.session.controller

import com.session.controller.api.SeatSessionController
import com.session.controller.api.request.dto.MovieDTO
import com.session.controller.api.request.dto.SeatDTO
import com.session.controller.api.request.dto.SeatSessionDTO
import com.session.controller.api.request.dto.SessionDTO
import com.session.enums.GenreEnum
import com.session.service.SeatSessionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class SeatSessionControllerTest {

    private lateinit var seatSessionService: SeatSessionService
    private lateinit var seatSessionController: SeatSessionController

    @BeforeEach
    fun setUp() {
        seatSessionService = mock(SeatSessionService::class.java)
        seatSessionController = SeatSessionController(seatSessionService)
    }

    @Test
    fun `should return all seat sessions`() {

        val movieDTO = MovieDTO(
            title = "Example Movie",
            synopsis = "This is an example movie.",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        val sessionDTO = SessionDTO(
            movie = movieDTO,
            startSession = LocalDateTime.of(2024, 10, 29, 15, 0),
            endSession = LocalDateTime.of(2024, 10, 29, 17, 0),
            initRangeTime = LocalDateTime.of(2024, 10, 29, 14, 30),
            endRangeTime = LocalDateTime.of(2024, 10, 29, 17, 30),
            description = "Evening show of Example Movie"
        )
        val seatSessions = listOf(SeatSessionDTO(seat = SeatDTO("A1"), availableSeats = 10, session = sessionDTO))
        `when`(seatSessionService.getAllSeatSessions()).thenReturn(seatSessions)

        val response: ResponseEntity<List<SeatSessionDTO>> = seatSessionController.getAllSeatSessions()

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == seatSessions)
    }

    @Test
    fun `should return seat session by ID`() {

        val movieDTO = MovieDTO(
            title = "Example Movie",
            synopsis = "This is an example movie.",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        val sessionDTO = SessionDTO(
            movie = movieDTO,
            startSession = LocalDateTime.of(2024, 10, 29, 15, 0),
            endSession = LocalDateTime.of(2024, 10, 29, 17, 0),
            initRangeTime = LocalDateTime.of(2024, 10, 29, 14, 30),
            endRangeTime = LocalDateTime.of(2024, 10, 29, 17, 30),
            description = "Evening show of Example Movie"
        )

        val seatSession = SeatSessionDTO(seat = SeatDTO("A1"), availableSeats = 10, session = sessionDTO)
        val seatSessionId = 1L
        `when`(seatSessionService.getSeatSessionById(seatSessionId)).thenReturn(seatSession)

        val response: ResponseEntity<SeatSessionDTO> = seatSessionController.getSeatSessionById(seatSessionId)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == seatSession)
    }

    @Test
    fun `should return NOT_FOUND for nonexistent seat session`() {
        val seatSessionId = 2L
        `when`(seatSessionService.getSeatSessionById(seatSessionId)).thenReturn(null)

        val response: ResponseEntity<SeatSessionDTO> = seatSessionController.getSeatSessionById(seatSessionId)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `should create a new seat session`() {

        val movieDTO = MovieDTO(
            title = "Example Movie",
            synopsis = "This is an example movie.",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        val sessionDTO = SessionDTO(
            movie = movieDTO,
            startSession = LocalDateTime.of(2024, 10, 29, 15, 0),
            endSession = LocalDateTime.of(2024, 10, 29, 17, 0),
            initRangeTime = LocalDateTime.of(2024, 10, 29, 14, 30),
            endRangeTime = LocalDateTime.of(2024, 10, 29, 17, 30),
            description = "Evening show of Example Movie"
        )

        val seatSessionDTO = SeatSessionDTO(seat = SeatDTO("A1"), availableSeats = 10, session = sessionDTO)
        val createdSeatSession = SeatSessionDTO(seat = SeatDTO("A1"), availableSeats = 10, session = sessionDTO)
        `when`(seatSessionService.createSeatSession(seatSessionDTO)).thenReturn(createdSeatSession)

        val response: ResponseEntity<SeatSessionDTO> = seatSessionController.createSeatSession(seatSessionDTO)

        assert(response.statusCode == HttpStatus.CREATED)
        assert(response.body == createdSeatSession)
    }

    @Test
    fun `should update an existing seat session`() {

        val movieDTO = MovieDTO(
            title = "Example Movie",
            synopsis = "This is an example movie.",
            duration = 120L,
            genre = GenreEnum.ACTION,
            classification = "PG-13"
        )

        val sessionDTO = SessionDTO(
            movie = movieDTO,
            startSession = LocalDateTime.of(2024, 10, 29, 15, 0),
            endSession = LocalDateTime.of(2024, 10, 29, 17, 0),
            initRangeTime = LocalDateTime.of(2024, 10, 29, 14, 30),
            endRangeTime = LocalDateTime.of(2024, 10, 29, 17, 30),
            description = "Evening show of Example Movie"
        )

        val seatSessionId = 1L
        val updatedSeatSession = SeatSessionDTO(seat = SeatDTO("A1"), availableSeats = 10, session = sessionDTO)
        `when`(seatSessionService.updateSeatSession(seatSessionId, updatedSeatSession)).thenReturn(updatedSeatSession)

        val response: ResponseEntity<SeatSessionDTO> = seatSessionController.updateSeatSession(seatSessionId, updatedSeatSession)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == updatedSeatSession)
    }

    @Test
    fun `should delete a seat session successfully`() {
        val seatSessionId = 1L
        `when`(seatSessionService.deleteSeatSession(seatSessionId)).thenReturn(true)

        val response: ResponseEntity<Void> = seatSessionController.deleteSeatSession(seatSessionId)

        assert(response.statusCode == HttpStatus.NO_CONTENT)
    }

    @Test
    fun `should return NOT_FOUND when deleting a nonexistent seat session`() {
        val seatSessionId = 2L
        `when`(seatSessionService.deleteSeatSession(seatSessionId)).thenReturn(false)

        val response: ResponseEntity<Void> = seatSessionController.deleteSeatSession(seatSessionId)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }
}