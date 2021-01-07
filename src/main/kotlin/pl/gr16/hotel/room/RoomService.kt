package pl.gr16.hotel.room

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoomService (
    private val roomRepository: RoomRepository) {
    fun findAll() = roomRepository.findAll()
}