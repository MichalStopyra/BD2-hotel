package pl.gr16.hotel.supply

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SupplyToStayService (
    private val supplyToStayRepository: SupplyToStayRepository) {
    fun findAll() = supplyToStayRepository.findAll()
}