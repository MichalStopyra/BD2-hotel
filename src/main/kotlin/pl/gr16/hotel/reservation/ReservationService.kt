package pl.gr16.hotel.reservation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReservationService (
    private val reservationRepository: ReservationRepository) {
    fun findAll() = reservationRepository.findAll()
}