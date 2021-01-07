package pl.gr16.hotel.supply

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlSupplyRepository: SupplyRepository {
    override fun findAll() =
            SupplyTable
                    .selectAll()
                    .map {
                        Supply (
                                it[SupplyTable.id].value,
                                it[SupplyTable.name],
                                it[SupplyTable.price],
                                it[SupplyTable.profit],
                                )
                    }
}
