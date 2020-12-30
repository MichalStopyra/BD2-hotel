package pl.gr16.hotel.stay

import java.math.BigDecimal
import java.time.LocalDate

enum class Status{
    FUTURE, ONGOING, FINISHED_UNPAID, FINISHED_PAID
}

data class Stay (
    val id: Int = 0,
    val fullPrice: BigDecimal,
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val standard: Status,
    val guests: String,
    val reservationId: Int,
    val roomId: Int
)
