package pl.gr16.hotel.room

import org.jetbrains.exposed.dao.id.IntIdTable
import pl.gr16.hotel.hotel.HotelTable
import java.math.BigDecimal

enum class RoomStandard {
        REGULAR, PREMIUM, VIP
}


data class Room (
        val id: Int = 0,
        val price: BigDecimal,
        val peopleNr: Int,
        val roomNr: Int,
        val description: String,
        val roomStandard: RoomStandard,
        val hotelId: Int
        )

object RoomTable : IntIdTable() {
        val price = decimal("price", 3, 2)
        val peopleNr = integer("peopleNr")
        val roomNr = integer("roomNr")
        val description = varchar("description", 250)
        val roomStandard = enumeration("roomStandard", RoomStandard::class/*.java*/)
        val hotelId = integer("hotel_id").references(HotelTable.id)
}