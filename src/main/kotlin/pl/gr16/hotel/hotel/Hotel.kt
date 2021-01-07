package pl.gr16.hotel.hotel

import org.jetbrains.exposed.dao.id.IntIdTable
enum class HotelStandard {
    TRZY_GWIAZKI, CZTERY_GWIAZDKI, PIEC_GWIAZDEK
}

data class Hotel (
        val id: Int = 0,
        val name: String,
        val adress: String,
        val hotelStandard: HotelStandard,
)

object HotelTable : IntIdTable() {
    val name = varchar("name", 50)
    val adress = varchar("adress", 250)
    val hotelStandard = enumeration("hotelStandard", HotelStandard::class/*.java*/)
}