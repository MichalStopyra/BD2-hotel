package pl.gr16.hotel.room

import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.springframework.stereotype.Component
import pl.gr16.hotel.hotel.HotelTable
import pl.gr16.hotel.room.RoomTable.price
import pl.gr16.hotel.stay.StayTable
import java.math.BigDecimal
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class SqlRoomRepository : RoomRepository {
    override fun findAll() =
            RoomTable
                    .selectAll()
                    .map {
                        Room(

                                it[RoomTable.id].value,
                                it[RoomTable.price],
                                it[RoomTable.peopleNr],
                                it[RoomTable.roomNr],
                                it[RoomTable.description],
                                it[RoomTable.roomStandard],
                                it[RoomTable.hotelId]
                        )
                    }

    override fun findAvailableRoomsForGivenDate(dateFrom: LocalDateTime, dateTo: LocalDateTime, roomStandard: RoomStandard,
                                                city: String, nrPeople: Int): List<Room> {


        var formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD")

        var dateFromFormatted = dateFrom.format(formatter)
        var dateToFormatted = dateTo.format(formatter)


        val query =
            """
                select * from room r 
                where r.people_nr >= $nrPeople
                and r.room_standard like '$roomStandard'
                and r.hotel_id = ( select * from (
                                                    select id from hotel
                                                        where adress like '$city' ) 
                                    where ROWNUM <= 1)
                and
               (r.id not in
                    (select room_id from stay s
                                        where ('$dateFromFormatted' between s.date_from and s.date_to )
                                            or ( '$dateToFormatted' between s.date_from and s.date_to )
                                            or ( '$dateFromFormatted' <= s.date_from and '$dateToFormatted' >= s.date_to )
                    )
               ) 
                """
        return query.execAndMap { rs ->
            Room(
                    id = rs.getInt("id"),
                    price = rs.getBigDecimal("price"),
                    peopleNr = rs.getInt("people_nr"),
                    roomNr = rs.getInt("room_nr"),
                    description = rs.getString("description"),
                    roomStandard = RoomStandard.valueOf(rs.getString("room_standard")),
                    hotelId = rs.getInt("hotel_id")
            )
        }

    }

    fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
        val result = arrayListOf<T>()
        TransactionManager.current().exec(this) { rs ->
            while (rs.next()) {
                result += transform(rs)
            }
        }
        return result
    }

}