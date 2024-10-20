package com.session.service

import com.session.controller.api.request.dto.SessionDTO
import com.session.entity.SessionAssembler
import com.session.repository.SessionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val assembler: SessionAssembler
) {

    fun getAllSessions(): List<SessionDTO> {
        return sessionRepository.findAll().map { session ->
            assembler.toDTO(session)
        }
    }

    fun getSessionById(id: Long): SessionDTO? {
        val session = sessionRepository.findById(id).orElseThrow {
            EntityNotFoundException("Session with ID $id not found")
        }
        return assembler.toDTO(session)
    }

    fun createSession(sessionDTO: SessionDTO): SessionDTO {
        return assembler.toDTO(sessionRepository.save(assembler.toEntity(sessionDTO)))
    }

    fun updateSession(id: Long, sessionDTO: SessionDTO): SessionDTO? {
        if (!sessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return assembler.toDTO(sessionRepository.save(assembler.toEntity(sessionDTO)))
    }

    fun deleteSession(id: Long) :Boolean {
        if (!sessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        sessionRepository.deleteById(id)
        return true
    }

}