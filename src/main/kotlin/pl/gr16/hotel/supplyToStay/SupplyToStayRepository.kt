package pl.gr16.hotel.supply

interface SupplyToStayRepository {
    fun findAll(): List<SupplyToStay>
}