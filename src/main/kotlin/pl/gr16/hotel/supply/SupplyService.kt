package pl.gr16.hotel.supply

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SupplyService (
    private val supplyRepository: SupplyToStayRepository) {
    fun findAll() = supplyRepository.findAll()
}