package pl.gr16.hotel.stay

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StayService (
    private val stayRepository: StayRepository) {
    fun findAll() = stayRepository.findAll()
}