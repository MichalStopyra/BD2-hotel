package pl.gr16.hotel.room

interface RoomRepository {
    fun findAll(): List<Room>
}