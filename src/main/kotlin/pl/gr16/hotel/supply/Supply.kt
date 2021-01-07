package pl.gr16.hotel.supply

import org.jetbrains.exposed.dao.id.IntIdTable
import java.math.BigDecimal


data class Supply(
        val id: Int = 0,
        val name: String,
        val price: BigDecimal,
        val profit: BigDecimal
)

object SupplyTable : IntIdTable() {
    val name = varchar("name", 50)
    val price = decimal("price", 3,2)
    val profit = decimal("profit", 3,2)
}
