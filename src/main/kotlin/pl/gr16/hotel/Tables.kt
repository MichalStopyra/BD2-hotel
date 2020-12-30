package pl.gr16.hotel

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import pl.gr16.hotel.hotel.HotelStandard
import pl.gr16.hotel.stay.Status
import pl.gr16.hotel.room.RoomStandard


object RoomTable : IntIdTable() {
    val description = varchar("description", 250)
    val roomStandard = enumeration("roomStandard", RoomStandard::class/*.java*/)
    val hotelId = integer("hotel_id").references(HotelTable.id)
}

object HotelTable : IntIdTable() {
    val name = varchar("name", 50)
    val adress = varchar("adress", 250)
    val hotelStandard = enumeration("hotelStandard", HotelStandard::class/*.java*/)
}

object ReservationTable : IntIdTable() {
    val userId = integer("user_id").references(UserTable.id)
}

object StayTable : IntIdTable() {
    val dateFrom = datetime("dateFrom")
    val dateTo = datetime("dateTo")
    val status = enumeration("status", Status::class)
    val guests = varchar("guests", 250)
    val reservationId = integer("reservation_id").references(ReservationTable.id)
    val roomId = integer("room_id").references(RoomTable.id)
}

object UserTable : IntIdTable() {
    val firstName = varchar("firstName", 50)
    val lastName = varchar("lastName", 50)
    val username = varchar("username", 50)
    val password = varchar("password", 50)
}