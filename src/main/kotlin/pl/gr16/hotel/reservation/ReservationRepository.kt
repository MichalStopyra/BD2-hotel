package pl.gr16.hotel.reservation

interface ReservationRepository {
    fun findAll(): List<Reservation>
}