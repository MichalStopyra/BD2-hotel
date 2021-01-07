package pl.gr16.hotel.reservation

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlReservationRepository: ReservationRepository {
    override fun findAll() =
            ReservationTable
                    .selectAll()
                    .map {
                        Reservation (
                                it[ReservationTable.id].value,
                                it[ReservationTable.userId]
                                )
                    }
}