package com.session.controller

import com.session.controller.api.SessionController
import com.session.controller.api.error.ResourceNotFoundException
import com.session.controller.api.request.dto.SessionDTO
import com.session.service.SessionService
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class SessionControllerTest {

    @Mock
    private lateinit var sessionService: SessionService

    @InjectMocks
    private lateinit var sessionController: SessionController

    @Test
    fun `test create session`() {
        val sessionDTO = SessionDTO(
            movie = null, // Preencher com um MovieDTO se necessário
            startSession = LocalDateTime.now(),
            endSession = LocalDateTime.now().plusHours(2),
            initRangeTime = LocalDateTime.now().minusMinutes(30),
            endRangeTime = LocalDateTime.now().plusMinutes(30),
            description = "Session Description"
        )

        `when`(sessionService.createSession(sessionDTO)).thenReturn(sessionDTO)

        val response: ResponseEntity<SessionDTO> = sessionController.createSession(sessionDTO)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(sessionDTO, response.body)
    }

    @Test
    fun `test get session by ID`() {
        val sessionDTO = SessionDTO(
            movie = null, // Preencher com um MovieDTO se necessário
            startSession = LocalDateTime.now(),
            endSession = LocalDateTime.now().plusHours(2),
            initRangeTime = LocalDateTime.now().minusMinutes(30),
            endRangeTime = LocalDateTime.now().plusMinutes(30),
            description = "Session Description"
        )

        `when`(sessionService.getSessionById(1L)).thenReturn(sessionDTO)

        val response: ResponseEntity<SessionDTO> = sessionController.getSessionById(1L)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(sessionDTO, response.body)
    }

    @Test
    fun `test update session`() {
        val sessionDTO = SessionDTO(
            movie = null, // Preencher com um MovieDTO se necessário
            startSession = LocalDateTime.now(),
            endSession = LocalDateTime.now().plusHours(2),
            initRangeTime = LocalDateTime.now().minusMinutes(30),
            endRangeTime = LocalDateTime.now().plusMinutes(30),
            description = "Updated Session Description"
        )

        `when`(sessionService.updateSession(1L, sessionDTO)).thenReturn(sessionDTO)

        val response: ResponseEntity<SessionDTO> = sessionController.updateSession(1L, sessionDTO)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(sessionDTO, response.body)
    }

    @Test
    fun `test delete session`() {
        `when`(sessionService.deleteSession(1L)).thenReturn(true)

        val response: ResponseEntity<Void> = sessionController.deleteSession(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `test delete session throws EntityNotFoundException`() {
        val sessionIdToDelete = 1L

        `when`(sessionService.deleteSession(sessionIdToDelete)).thenThrow(EntityNotFoundException("Recurso com ID $sessionIdToDelete não foi encontrado."))

        val response: ResponseEntity<Void> = sessionController.deleteSession(sessionIdToDelete)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}