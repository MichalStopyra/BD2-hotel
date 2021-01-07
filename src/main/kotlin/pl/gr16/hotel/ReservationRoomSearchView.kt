package pl.gr16.hotel

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.router.Route
import java.math.BigDecimal
import java.math.BigInteger.TWO
import java.time.LocalDate

@Route("")
class ReservationRoomSearchView(
  reservationFacade: ReservationFacade
) : KComposite() {
  private val binder = beanValidationBinder<RoomSearchForm>()
  private val cities = reservationFacade.getHotelCities()
  val bean = RoomSearchForm(
    LocalDate.MIN,
    LocalDate.MAX,
    1,
    searchRooms(1)
  )
  private val grid = Grid(RoomDto::class.java)

  private val root = ui {
    horizontalLayout {
      verticalLayout {
        formLayout {
          datePicker("From:") {
            bind(binder).bind(RoomSearchForm::dateFrom)
          }
          datePicker("To:") {
            bind(binder).bind(RoomSearchForm::dateTo)
          }
          comboBox<HotelCities>("City:") {
            setItems(*cities)
          }
          integerField("People:") {
            bind(binder).bind(RoomSearchForm::peopleNumber)
          }
          button("Search") {
            onLeftClick {
              binder.writeBean(bean)
              val rooms = searchRooms(bean.peopleNumber)
              bean.rooms = rooms
              println(bean.rooms)
              binder.readBean(bean)
              grid.setItems(bean.rooms)
            }
          }
        }
      }
      verticalLayout {
        add(grid)
      }
    }
  }

  fun searchRooms(peopleNumber: Int): List<RoomDto> {
    return if (peopleNumber == 1) {
      listOf(RoomDto(
        1,
        BigDecimal.ONE,
        1,
        "Nice room for 1 person"
      ))
    } else {
      listOf(RoomDto(
        2,
        BigDecimal.TEN,
        2,
        "Nice room for 2 people"
      ))
    }
  }
}

data class RoomDto(
  val id: Int,
  val price: BigDecimal,
  val peopleNumber: Int,
  val description: String
)

data class RoomSearchForm(
  var dateFrom: LocalDate,
  var dateTo: LocalDate,
  var peopleNumber: Int,
  var rooms: List<RoomDto>
)