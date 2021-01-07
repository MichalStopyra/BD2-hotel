package pl.gr16.hotel.room

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class SqlRoomRepository: RoomRepository {
    override fun findAll() =
            RoomTable
                    .selectAll()
                    .map {
                        Room (

                                it[RoomTable.id].value,
                                it[RoomTable.price],
                                it[RoomTable.peopleNr],
                                it[RoomTable.roomNr],
                                it[RoomTable.description],
                                it[RoomTable.roomStandard],
                                it[RoomTable.hotelId]
                                )
                    }
}