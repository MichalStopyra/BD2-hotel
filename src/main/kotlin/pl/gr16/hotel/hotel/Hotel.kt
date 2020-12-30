package pl.gr16.hotel.hotel

import java.math.BigDecimal
enum class HotelStandard {
    THREE_STARS, FOUR_STARS, FIVE_STARS
}

data class Hotel (
        val id: Int = 0,
        val name: String,
        val adress: String,
        val hotelStandard: HotelStandard,
        val fixedCost: BigDecimal
)