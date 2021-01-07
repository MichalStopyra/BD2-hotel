package pl.gr16.hotel.hotel

import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class SqlHotelRepository: HotelRepository {
    override fun findAll() =
            HotelTable
                    .selectAll()
                    .map {
                        Hotel (
                                it[HotelTable.id].value,
                                it[HotelTable.name],
                                it[HotelTable.adress],
                                it[HotelTable.hotelStandard]
                                )
                    }
}