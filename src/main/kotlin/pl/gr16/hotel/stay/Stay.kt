package pl.gr16.hotel.stay

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import pl.gr16.hotel.reservation.ReservationTable
import pl.gr16.hotel.room.RoomTable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

enum class Status{
    PRZYSZLE, TRWAJACE, SKONCZONE_NIEZAPLACONE, SKONCZONE_ZAPLACONE
}

data class Stay(
        val id: Int = 0,
        val fullPrice: BigDecimal,
        val dateFrom: LocalDateTime,
        val dateTo: LocalDateTime,
        val status: Status,
        val guests: String,
        val reservationId: Int,
        val roomId: Int
)

object StayTable : IntIdTable() {
    val fullPrice = decimal("FULL_PRICE", 3, 2)
    val dateFrom = datetime("DATE_FROM")
    val dateTo = datetime("DATE_TO")
    val status = enumeration("status", Status::class)
    val guests = varchar("guests", 250)
    val reservationId = integer("reservation_id").references(ReservationTable.id)
    val roomId = integer("room_id").references(RoomTable.id)
}
