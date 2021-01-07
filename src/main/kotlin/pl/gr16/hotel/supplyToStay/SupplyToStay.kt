package pl.gr16.hotel.supply

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import pl.gr16.hotel.hotel.HotelTable
import pl.gr16.hotel.room.RoomTable
import pl.gr16.hotel.room.RoomTable.references
import pl.gr16.hotel.stay.StayTable
import java.math.BigDecimal


data class SupplyToStay(
        val id: Int = 0,
        val quantity: Int,
        val supplyId: Int,
        val stayId: Int
)

object SupplyToStayTable : IntIdTable("Supply_to_stay") {
    val quantity = integer("quantity")
    val supplyId = integer("supply_id").references(SupplyTable.id)
    val stayId = integer("stay_id").references(StayTable.id)
}
