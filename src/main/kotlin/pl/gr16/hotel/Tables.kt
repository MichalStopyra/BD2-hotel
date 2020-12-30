package pl.gr16.hotel

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table
import pl.gr16.hotel.stay.Status
import java.time.LocalDate
import pl.gr16.hotel.room.RoomStandard


object RoomTable : Table("rooms") {
    val description = varchar("description", length = 250)
    val roomStandard: RoomStandard,
    val hotelId = integer("hotel_id").references(HotelTable.id)
}

object HotelTable : IntIdTable() {
    val name = varchar("name", length = 250)
    val adress = varchar("adress", length = 250)
}

object ReservationTable : IntIdTable() {
    val userId = integer("user_id").references(UserTable.id)
}

object StayTable : IntIdTable() {
    val dateFrom = JavaTypeLo
    val dateTo: LocalDate,
    val standard: Status,
    val guests = varchar("guests", length = 250)
    val reservationId = integer("reservation_id").references(ReservationTable.id)
    val roomId = integer("room_id").references(RoomTable.id)
}

object UserTable : IntIdTable() {
    val username = varchar("username", length = 50)
    val password = varchar("password", length = 250)
}