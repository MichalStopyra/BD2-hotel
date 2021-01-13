package pl.gr16.hotel

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.select.Select
import com.vaadin.flow.router.Route
import pl.gr16.hotel.room.RoomService
import pl.gr16.hotel.room.RoomStandard
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Route("")
class ReservationRoomSearchView(
        reservationFacade: ReservationFacade,
        roomService: RoomService
) : KComposite() {
  var citySelect: Select<String> = Select()
  var roomStandardSelect: Select<String> = Select()

  private val binder = beanValidationBinder<RoomSearchForm>()
  private val cities = reservationFacade.getHotelCities()
  private val roomStandards = reservationFacade.getRoomStandard()
  private val roomService = roomService;
  val bean = RoomSearchForm(
          LocalDate.MIN,
          LocalDate.MAX,
          1,
          searchRooms(LocalDateTime.now(), LocalDateTime.now().plusDays(1), RoomStandard.REGULAR, "Warszawa", 1),
          "REGULAR",
          "Warszawa"
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
            binder.forField(citySelect)
            .bind(RoomSearchForm::city)
          }
          comboBox<RoomStandard>("Room Standard: ") {
            setItems(*roomStandards)
            binder.forField(roomStandardSelect)
                    .bind(RoomSearchForm::roomStandard)
          }
          integerField("People:") {
            bind(binder).bind(RoomSearchForm::peopleNumber)
          }
          button("Search") {
            onLeftClick {
              binder.writeBean(bean)
              val rooms = searchRooms(bean.dateFrom.atStartOfDay(), bean.dateTo.atStartOfDay(),
                      RoomStandard.valueOf(bean.roomStandard), bean.city,
                      bean.peopleNumber)
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

  fun searchRooms(dateFrom: LocalDateTime, dateTo: LocalDateTime, roomStandard: RoomStandard,
                  city: String, peopleNumber: Int): List<RoomDto> {
    return roomService.findMatchingRooms(dateFrom, dateTo, roomStandard, city, peopleNumber)
//    return if (peopleNumber == 1) {
//      listOf(RoomDto(
//        1,
//        BigDecimal.ONE,
//        1,
//        "Nice room for 1 person"
//      ))
//    } else {
//      listOf(RoomDto(
//        2,
//        BigDecimal.TEN,
//        2,
//        "Nice room for 2 people"
//      ))
//    }
  }
}

data class RoomDto(
        val id: Int,
        val price: BigDecimal,
        val peopleNumber: Int,
        val description: String,
        val roomStandard: String
)


data class RoomSearchForm(
        var dateFrom: LocalDate,
        var dateTo: LocalDate,
        var peopleNumber: Int,
        var rooms: List<RoomDto>,
        var roomStandard: String,
        var city: String
)