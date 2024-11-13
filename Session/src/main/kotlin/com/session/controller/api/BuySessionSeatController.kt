package com.session.controller.api

import com.session.controller.api.request.dto.BuySessionSeatDTO
import com.session.service.BuySessionSeatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/buySessionSeats")
class BuySessionSeatController @Autowired constructor(
    private val buySessionSeatService: BuySessionSeatService
) {

    @PostMapping
    fun createBuySessionSeat(@RequestBody buySessionSeatDTO: BuySessionSeatDTO): ResponseEntity<BuySessionSeatDTO> {
        val createdSeat = buySessionSeatService.createBuySessionSeat(buySessionSeatDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeat)
    }

    @GetMapping("/{id}")
    fun getBuySessionSeatById(@PathVariable id: Long): ResponseEntity<BuySessionSeatDTO> {
        val buySessionSeat = buySessionSeatService.getBuySessionSeatById(id)
        return if (buySessionSeat != null) {
            ResponseEntity.ok(buySessionSeat)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBuySessionSeat(@PathVariable id: Long): ResponseEntity<Void> {
        return if (buySessionSeatService.deleteBuySessionSeat(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
