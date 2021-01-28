package pl.gr16.hotel

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.html.Image
import pl.gr16.hotel.room.RoomStandard
import pl.gr16.hotel.stay.Status
import pl.gr16.hotel.stay.Stay
import pl.gr16.hotel.stay.StayService
import java.math.BigDecimal
import java.time.LocalDate


class ReservationFormView(
    stayService: StayService
) : KComposite() {

    private var parameters = RoomSearchForm(
        LocalDate.now(),
        LocalDate.now().plusDays(8),
        1,
        RoomStandard.REGULAR,
        HotelCities.Warszawa
    )
    private var room = RoomDto(
        0,
        BigDecimal.ONE,
        1,
        " ",
        RoomStandard.REGULAR,
    )
    private var binder = beanValidationBinder<Guests>()

    private val stayService = stayService
    val bean = Guests(
        " "
    )
    val dialog = Dialog()


    private var root = ui {
        dialog.add(Text("Added new Reservation for parameters:\n"))

        dialog.setWidth("400px")
        dialog.setHeight("150px")

        verticalLayout {
            formLayout {
                textField("Guests (seperated with coma)") {
                    bind(binder).bind(Guests::guests)
                }
                button("Apply") {
                    onLeftClick {
                        binder.writeBean(bean)
                        val stay = Stay(
                            0,
                            BigDecimal.ONE,
                            parameters.dateFrom.atTime(3, 0),
                            parameters.dateTo.atTime(12, 0),
                            Status.PRZYSZLE,
                            bean.guests,
                            1,
                            room.id
                        )
                        saveStay(stay)
                    }
                }
            }
        }
    }


    private fun saveStay(stay: Stay) {
        stayService.saveStay(stay)
        this.isVisible = false
        dialog.add(Text("dateFrom: " + stay.dateFrom.toString() + " "))
        dialog.add(Text("dateTo: " + stay.dateTo.toString() + " "))
        dialog.add(Text("guests: " + stay.guests))
        dialog.open()

    }

    public fun setParameters(roomSearchForm: RoomSearchForm, chosenRoom: RoomDto
    ) {
        parameters = roomSearchForm
        room = chosenRoom
    }
}

data class Guests(
    var guests: String
)

data class StayForReservation(
    var dateFrom: LocalDate,
    var dateTo: LocalDate,
    var peopleNumber: Int,
    var roomId: Int,
    var guests: String
)