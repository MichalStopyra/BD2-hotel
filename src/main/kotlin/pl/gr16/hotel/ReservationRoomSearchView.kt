package pl.gr16.hotel

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.router.Route
import java.time.LocalDate

@Route("")
class ReservationRoomSearchView(
  reservationFacade: ReservationFacade
) : KComposite() {
  private val binder = beanValidationBinder<RoomSearchForm>()
  private val cities = reservationFacade.getHotelCities()

  private val root = ui {
    verticalLayout {
      formLayout {
        datePicker("From:") {
          bind(binder).bind(RoomSearchForm::dateFrom)
        }
        datePicker("To:") {
          bind(binder).bind(RoomSearchForm::dateTo)
        }
        comboBox<HotelCities>("City:"){
          setItems(*cities)
        }
        integerField("People:") {
          bind(binder).bind(RoomSearchForm::peopleNumber)
        }
      }
    }
  }
}

data class RoomSearchForm(
  var dateFrom: LocalDate,
  var dateTo: LocalDate,
  var peopleNumber: Int
)