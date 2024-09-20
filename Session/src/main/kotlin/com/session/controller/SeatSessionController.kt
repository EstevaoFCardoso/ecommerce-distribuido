package com.session.controller

import com.session.entity.SeatSessionEntity
import com.session.service.SeatSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seat-sessions")
class SeatSessionController(
    private val seatSessionService: SeatSessionService
) {

    @GetMapping
    fun getAllSeatSessions(): ResponseEntity<List<SeatSessionEntity>> {
        return ResponseEntity.ok(seatSessionService.getAllSeatSessions())
    }

    @GetMapping("/{id}")
    fun getSeatSessionById(@PathVariable id: Long): ResponseEntity<SeatSessionEntity> {
        val seatSession = seatSessionService.getSeatSessionById(id)
        return if (seatSession != null) {
            ResponseEntity.ok(seatSession)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSeatSession(@RequestBody seatSessionEntity: SeatSessionEntity): ResponseEntity<SeatSessionEntity> {
        val newSeatSession = seatSessionService.createSeatSession(seatSessionEntity)
        return ResponseEntity(newSeatSession, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateSeatSession(@PathVariable id: Long, @RequestBody seatSessionEntity: SeatSessionEntity): ResponseEntity<SeatSessionEntity> {
        val updatedSeatSession = seatSessionService.updateSeatSession(id, seatSessionEntity)
        return if (updatedSeatSession != null) {
            ResponseEntity.ok(updatedSeatSession)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSeatSession(@PathVariable id: Long): ResponseEntity<Void> {
        seatSessionService.deleteSeatSession(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}