package com.session.service

import com.session.entity.SessionEntity
import com.session.repository.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository
) {

    fun getAllSessions(): List<SessionEntity> {
        return sessionRepository.findAll()
    }

    fun getSessionById(id: Long): SessionEntity? {
        return sessionRepository.findById(id).orElse(null)
    }

    fun createSession(sessionEntity: SessionEntity): SessionEntity {
        return sessionRepository.save(sessionEntity)
    }

    fun updateSession(id: Long, sessionEntity: SessionEntity): SessionEntity? {
        return if (sessionRepository.existsById(id)) {
            sessionEntity.id = id
            sessionRepository.save(sessionEntity)
        } else {
            null
        }
    }

    fun deleteSession(id: Long) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id)
        }
    }

}