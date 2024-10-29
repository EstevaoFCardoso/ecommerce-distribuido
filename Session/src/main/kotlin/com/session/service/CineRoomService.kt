package com.session.service

import com.session.controller.api.error.CineRoomNotFoundException
import com.session.controller.api.request.dto.CineRoomDTO
import com.session.entity.CineRoomAssembler
import com.session.repository.CineRoomRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CineRoomService(
    private val cineRoomRepository: CineRoomRepository,
    private val cineRoomAssembler: CineRoomAssembler
) {

    fun getAllCineRooms(): List<CineRoomDTO> {
        return cineRoomRepository.findAll().map { cineRoom ->
            cineRoomAssembler.toDTO(cineRoom)
        }
    }

    fun getCineRoomById(id: Long): CineRoomDTO? {
        val cineRoomDTO = cineRoomRepository.findById(id).orElseThrow {
            CineRoomNotFoundException("CineRoom with ID $id not found")
        }
        return cineRoomAssembler.toDTO(cineRoomDTO)
    }

    fun createCineRoom(cineRoomDTO: CineRoomDTO): CineRoomDTO {
        return cineRoomAssembler.toDTO(cineRoomRepository.save(cineRoomAssembler.toEntity(cineRoomDTO)))
    }

    fun updateCineRoom(id: Long, cineRoomDTO: CineRoomDTO): CineRoomDTO? {
        if (!cineRoomRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return cineRoomAssembler.toDTO(cineRoomRepository.save(cineRoomAssembler.toEntity(cineRoomDTO)))
    }

    fun deleteCineRoom(id: Long): Boolean {
        if (!cineRoomRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        cineRoomRepository.deleteById(id)
        return true
    }
}