package pl.gr16.hotel.room

import java.math.BigDecimal

enum class RoomStandard {
        REGULAR, PREMIUM, VIP

}

data class Room (
        val id: Int = 0,
        val price: BigDecimal,
        val peopleNr: Int,
        val roomNr: Int,
        val description: String,
        val roomStandard: RoomStandard,
        val hotelId: Int
        )