package com.session.service

import com.session.entity.CineRoomEntity
import com.session.repository.CineRoomRepository
import org.springframework.stereotype.Service

@Service
class CineRoomService(
    private val cineRoomRepository: CineRoomRepository
) {

    fun getAllCineRooms(): List<CineRoomEntity> {
        return cineRoomRepository.findAll()
    }

    fun getCineRoomById(id: Long): CineRoomEntity? {
        return cineRoomRepository.findById(id).orElse(null)
    }

    fun createCineRoom(cineRoomEntity: CineRoomEntity): CineRoomEntity {
        return cineRoomRepository.save(cineRoomEntity)
    }

    fun updateCineRoom(id: Long, cineRoomEntity: CineRoomEntity): CineRoomEntity? {
        return if (cineRoomRepository.existsById(id)) {
            cineRoomEntity.id = id
            cineRoomRepository.save(cineRoomEntity)
        } else {
            null
        }
    }

    fun deleteCineRoom(id: Long) {
        if (cineRoomRepository.existsById(id)) {
            cineRoomRepository.deleteById(id)
        }
    }
}