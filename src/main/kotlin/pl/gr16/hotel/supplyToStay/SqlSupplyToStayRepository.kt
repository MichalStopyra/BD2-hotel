package pl.gr16.hotel.supply

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlSupplyToStayRepository: SupplyToStayRepository {
    override fun findAll() =
            SupplyToStayTable
                    .selectAll()
                    .map {
                        SupplyToStay (
                                it[SupplyToStayTable.id].value,
                                it[SupplyToStayTable.quantity],
                                it[SupplyToStayTable.supplyId],
                                it[SupplyToStayTable.stayId]
                                )
                    }
}
