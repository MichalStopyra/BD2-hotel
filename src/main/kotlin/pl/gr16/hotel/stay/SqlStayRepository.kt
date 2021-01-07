package pl.gr16.hotel.stay

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlStayRepository: StayRepository {
    override fun findAll() =
            StayTable
                    .selectAll()
                    .map {
                        Stay (
                                it[StayTable.id].value,
                                it[StayTable.fullPrice],
                                it[StayTable.dateFrom],
                                it[StayTable.dateTo],
                                it[StayTable.status],
                                it[StayTable.guests],
                                it[StayTable.reservationId],
                                it[StayTable.roomId]
                                )
                    }
}