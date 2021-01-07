package pl.gr16.hotel.hotel

interface HotelRepository {
    fun findAll(): List<Hotel>
}