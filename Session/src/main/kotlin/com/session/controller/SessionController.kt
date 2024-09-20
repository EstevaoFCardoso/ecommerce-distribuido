package com.session.controller

import com.session.entity.SessionEntity
import com.session.service.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionService: SessionService
) {

    @GetMapping
    fun getAllSessions(): ResponseEntity<List<SessionEntity>> {
        return ResponseEntity.ok(sessionService.getAllSessions())
    }

    @GetMapping("/{id}")
    fun getSessionById(@PathVariable id: Long): ResponseEntity<SessionEntity> {
        val session = sessionService.getSessionById(id)
        return if (session != null) {
            ResponseEntity.ok(session)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSession(@RequestBody sessionEntity: SessionEntity): ResponseEntity<SessionEntity> {
        val newSession = sessionService.createSession(sessionEntity)
        return ResponseEntity(newSession, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateSession(@PathVariable id: Long, @RequestBody sessionEntity: SessionEntity): ResponseEntity<SessionEntity> {
        val updatedSession = sessionService.updateSession(id, sessionEntity)
        return if (updatedSession != null) {
            ResponseEntity.ok(updatedSession)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSession(@PathVariable id: Long): ResponseEntity<Void> {
        sessionService.deleteSession(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}