package com.session.controller

import com.session.entity.SeatEntity
import com.session.service.SeatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seats")
class SeatController(
    private val seatService: SeatService
) {

    @GetMapping
    fun getAllSeats(): ResponseEntity<List<SeatEntity>> {
        return ResponseEntity.ok(seatService.getAllSeats())
    }

    @GetMapping("/{id}")
    fun getSeatById(@PathVariable id: Long): ResponseEntity<SeatEntity> {
        val seat = seatService.getSeatById(id)
        return if (seat != null) {
            ResponseEntity.ok(seat)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSeat(@RequestBody seatEntity: SeatEntity): ResponseEntity<SeatEntity> {
        val newSeat = seatService.createSeat(seatEntity)
        return ResponseEntity(newSeat, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateSeat(@PathVariable id: Long, @RequestBody seatEntity: SeatEntity): ResponseEntity<SeatEntity> {
        val updatedSeat = seatService.updateSeat(id, seatEntity)
        return if (updatedSeat != null) {
            ResponseEntity.ok(updatedSeat)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSeat(@PathVariable id: Long): ResponseEntity<Void> {
        seatService.deleteSeat(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}