package com.session.controller

import com.session.entity.CineRoomEntity
import com.session.service.CineRoomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cine-rooms")
class CineRoomController(
    private val cineRoomService: CineRoomService
) {

    @GetMapping
    fun getAllCineRooms(): ResponseEntity<List<CineRoomEntity>> {
        return ResponseEntity.ok(cineRoomService.getAllCineRooms())
    }

    @GetMapping("/{id}")
    fun getCineRoomById(@PathVariable id: Long): ResponseEntity<CineRoomEntity> {
        val cineRoom = cineRoomService.getCineRoomById(id)
        return if (cineRoom != null) {
            ResponseEntity.ok(cineRoom)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createCineRoom(@RequestBody cineRoomEntity: CineRoomEntity): ResponseEntity<CineRoomEntity> {
        val newCineRoom = cineRoomService.createCineRoom(cineRoomEntity)
        return ResponseEntity(newCineRoom, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCineRoom(@PathVariable id: Long, @RequestBody cineRoomEntity: CineRoomEntity): ResponseEntity<CineRoomEntity> {
        val updatedCineRoom = cineRoomService.updateCineRoom(id, cineRoomEntity)
        return if (updatedCineRoom != null) {
            ResponseEntity.ok(updatedCineRoom)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCineRoom(@PathVariable id: Long): ResponseEntity<Void> {
        cineRoomService.deleteCineRoom(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}