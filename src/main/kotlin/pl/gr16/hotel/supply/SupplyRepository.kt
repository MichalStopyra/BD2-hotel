package pl.gr16.hotel.supply

interface SupplyRepository {
    fun findAll(): List<Supply>
}