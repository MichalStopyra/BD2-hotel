package pl.gr16.hotel.reservation

import org.jetbrains.exposed.dao.id.IntIdTable
import pl.gr16.hotel.user.UserTable

data class Reservation(
        val id: Int = 0,
        val userId: Int
)

object ReservationTable : IntIdTable() {
    val userId = integer("user_id").references(UserTable.id)
}
