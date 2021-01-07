package pl.gr16.hotel.stay

interface StayRepository {
    fun findAll(): List<Stay>
}