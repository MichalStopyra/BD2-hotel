package pl.gr16.hotel.hotel

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HotelService (
    private val hotelRepository: HotelRepository ) {
    fun findAll() = hotelRepository.findAll()
}