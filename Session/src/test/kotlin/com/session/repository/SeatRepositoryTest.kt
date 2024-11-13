package com.session.repository

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import templates.SeatTemplate
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@DataJpaTest
class SeatRepositoryTest {

    @Autowired
    lateinit var seatRepository: SeatRepository

    @Test
    fun `should save a seat`() {
        val seat = SeatTemplate.validSeatEntity(name = "A1")
        val savedSeat = seatRepository.save(seat)

        assertEquals("A1", savedSeat.name)
        assert(savedSeat.id > 0) // A verificação do ID depende de como está configurado o banco de dados
    }

    @Test
    fun `should find seat by ID`() {
        val seat = SeatTemplate.validSeatEntity(name = "A1")
        val savedSeat = seatRepository.save(seat)

        val foundSeat = seatRepository.findById(savedSeat.id).orElse(null)

        assertEquals(savedSeat.name, foundSeat?.name)
    }

    @Test
    fun `should delete a seat`() {
        val seat = SeatTemplate.validSeatEntity(name = "A1")
        val savedSeat = seatRepository.save(seat)

        seatRepository.deleteById(savedSeat.id)

        val foundSeat = seatRepository.findById(savedSeat.id).orElse(null)
        assert(foundSeat == null)
    }

}