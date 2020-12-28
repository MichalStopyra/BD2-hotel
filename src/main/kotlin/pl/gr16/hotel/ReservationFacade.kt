package pl.gr16.hotel

import org.springframework.stereotype.Component

@Component
class ReservationFacade {
  fun getHotelCities() = HotelCities.values()

}

enum class HotelCities {
  A, B, C
}

