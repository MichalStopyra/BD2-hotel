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
  var citySelect: Select<HotelCities> = Select()
  var roomStandardSelect: Select<RoomStandard> = Select()

  private val binder = beanValidationBinder<RoomSearchForm>()
  private val cities = reservationFacade.getHotelCities()
  private val roomStandards = reservationFacade.getRoomStandard()
  private val roomService = roomService;
  val bean = RoomSearchForm(
          LocalDate.now(),
          LocalDate.now().plusDays(8),
          1,
       //   searchRooms(LocalDateTime.now(), LocalDateTime.now().plusDays(1), RoomStandard.REGULAR, "Warszawa", 1),
RoomStandard.REGULAR,
          HotelCities.Warszawa
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
                    .asRequired(
                            "Please choose the appropriate city")
            .bind(RoomSearchForm::city)
          }
          comboBox<RoomStandard>("Room Standard: ") {
            setItems(*roomStandards)
            binder.forField(roomStandardSelect)
                    .asRequired(
                            "Please choose the appropriate roomStandard")
                    .bind(RoomSearchForm::roomStandard)
          }
          integerField("People:") {
            bind(binder).bind(RoomSearchForm::peopleNumber)
          }
          button("Search") {
            onLeftClick {
              binder.writeBeanIfValid(bean)
              println(bean.city)
              val rooms = searchRooms(bean.dateFrom.atStartOfDay(), bean.dateTo.atStartOfDay(),
                      bean.roomStandard, bean.city,
                      bean.peopleNumber)
           //   bean.rooms = rooms
             // println(bean.rooms)
              binder.readBean(bean)
              grid.setItems(rooms)
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
                  city: HotelCities, peopleNumber: Int): List<RoomDto> {
    return roomService.findMatchingRooms(dateFrom, dateTo, roomStandard, city, peopleNumber)
  }
}

data class RoomDto(
        val id: Int,
        val price: BigDecimal,
        val peopleNumber: Int,
        val description: String,
        val roomStandard: RoomStandard
)


data class RoomSearchForm(
        var dateFrom: LocalDate,
        var dateTo: LocalDate,
        var peopleNumber: Int,
      //  var rooms: List<RoomDto>,
        var roomStandard: RoomStandard,
        var city: HotelCities
)