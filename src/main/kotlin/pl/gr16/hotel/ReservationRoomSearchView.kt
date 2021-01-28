package pl.gr16.hotel

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.router.Route
import pl.gr16.hotel.room.RoomService
import pl.gr16.hotel.room.RoomStandard
import pl.gr16.hotel.stay.StayService
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Route("")
class ReservationRoomSearchView(
    reservationFacade: ReservationFacade,
    roomService: RoomService,
    stayService: StayService
) : KComposite() {


    private var binder = beanValidationBinder<RoomSearchForm>()
    private val cities = reservationFacade.getHotelCities()
    private val roomStandards = reservationFacade.getRoomStandard()
    private val roomService = roomService;
    private val stayService = stayService;

    val bean = RoomSearchForm(
        LocalDate.now(),
        LocalDate.now().plusDays(8),
        1,
        //   searchRooms(LocalDateTime.now(), LocalDateTime.now().plusDays(1), RoomStandard.REGULAR, "Warszawa", 1),
        RoomStandard.REGULAR,
        HotelCities.Warszawa
    )
    val bean2 = RoomSearchForm(
        LocalDate.now(),
        LocalDate.now().plusDays(8),
        1,
        //   searchRooms(LocalDateTime.now(), LocalDateTime.now().plusDays(1), RoomStandard.REGULAR, "Warszawa", 1),
        RoomStandard.REGULAR,
        HotelCities.Warszawa
    )
    private val grid = Grid(RoomDto::class.java)
    private var reservationView = ReservationFormView(stayService)
    private val root = ui {
        reservationView.isVisible = false
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
                        setItems(*HotelCities.values())
                        bind(binder).bind(RoomSearchForm::city)
                    }
                    comboBox<RoomStandard>("Room Standard: ") {
                        setItems(*RoomStandard.values())
                        bind(binder).bind(RoomSearchForm::roomStandard)
                    }
                    integerField("People:") {
                        bind(binder).bind(RoomSearchForm::peopleNumber)
                    }
                    button("Search") {
                        onLeftClick {
                            binder.writeBean(bean)
                            val rooms = searchRooms(
                                bean.dateFrom.atStartOfDay(), bean.dateTo.atStartOfDay(),
                                bean.roomStandard, bean.city,
                                bean.peopleNumber
                            )
                            binder.readBean(bean)
                            configureGrid(rooms)

                        }
                    }
                }
            }
            verticalLayout {
                add(grid)
                add(reservationView)
            }
        }
    }



    private fun configureGrid(rooms: List<RoomDto>) {
        grid.setItems(rooms)
        grid.asSingleSelect().addValueChangeListener { event -> displayReservationForm(event.value) }
    }

    private fun displayReservationForm(value: RoomDto?) {
        if ( value == null ){
            closeReservationForm()
            return
        }
        reservationView.setParameters(bean, value )
        reservationView.isVisible = true


    }

    private fun closeReservationForm() {
      //  reservationView.removeComponents()
        reservationView.isVisible = false

    }

    fun searchRooms(
        dateFrom: LocalDateTime, dateTo: LocalDateTime, roomStandard: RoomStandard,
        city: HotelCities, peopleNumber: Int
    ): List<RoomDto> {
        return roomService.findMatchingRooms(dateFrom, dateTo, roomStandard, city, peopleNumber)
    }


}

data class RoomDto(
    val id: Int,
    val price: BigDecimal,
    val peopleNumber: Int,
    val description: String,
    val roomStandard: RoomStandard,
)


data class RoomSearchForm(
    var dateFrom: LocalDate,
    var dateTo: LocalDate,
    var peopleNumber: Int,
    //  var rooms: List<RoomDto>,
    var roomStandard: RoomStandard,
    var city: HotelCities
)