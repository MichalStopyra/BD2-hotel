package pl.gr16.hotel.room

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.gr16.hotel.HotelCities
import pl.gr16.hotel.RoomDto
import java.time.LocalDateTime

@Service
@Transactional
class RoomService (
    private val roomRepository: RoomRepository) {
    fun findAll() = roomRepository.findAll()
    fun findMatchingRooms(dateFrom: LocalDateTime, dateTo: LocalDateTime, roomStandard: RoomStandard,
                          city: HotelCities, nrPeople: Int )
                                                        = roomRepository.findAvailableRoomsForGivenDate(
                                                        dateFrom, dateTo, roomStandard, city.toString(), nrPeople
                                                        ).map {toRoomDto(it) }
    fun toRoomDto (room: Room) : RoomDto {
        return RoomDto(room.id, room.price, room.peopleNr, room.description, room.roomStandard)
    }
}