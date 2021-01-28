package pl.gr16.hotel.stay

import org.jetbrains.exposed.sql.insert
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

    override fun createStay(stay: Stay) {
            StayTable
                    .insert {
                            it[StayTable.fullPrice] = stay.fullPrice
                            it[StayTable.dateFrom] = stay.dateFrom
                            it[StayTable.dateTo] = stay.dateTo
                            it[StayTable.status] = stay.status
                            it[StayTable.guests] = stay.guests
                            it[StayTable.roomId] = stay.roomId
                    }
    }

}