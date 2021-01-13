package pl.gr16.hotel.room

import java.time.LocalDateTime

interface RoomRepository {
    fun findAll(): List<Room>
    fun findAvailableRoomsForGivenDate(dateFrom: LocalDateTime, dateTo: LocalDateTime, roomStandard: RoomStandard,
                                       city: String, nrPeople: Int): List<Room>

}