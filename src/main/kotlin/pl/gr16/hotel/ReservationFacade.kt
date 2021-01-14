package pl.gr16.hotel

import org.springframework.stereotype.Component
import pl.gr16.hotel.room.RoomStandard

@Component
class ReservationFacade {
  fun getHotelCities() = HotelCities.values()
  fun getRoomStandard() = RoomStandard.values()
}

enum class HotelCities {
  Warszawa, Gdansk, Wroclaw
}
