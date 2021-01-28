package pl.gr16.hotel.balancesheet

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.data.binder.ValidationException
import com.vaadin.flow.router.Route
import pl.gr16.hotel.HotelCities
import java.math.BigDecimal
import java.time.Clock
import java.time.Month
import java.time.Year
import java.time.YearMonth
import java.util.function.Consumer


@Route("balance")
class BalanceSheetView(private val balanceSheetApi: BalanceSheetApi,
                       clock: Clock) : VerticalLayout() {
  val binder = BeanValidationBinder(BalanceSheetFormDto::class.java)
  private val balanceSheetFormDto: BalanceSheetFormDto = BalanceSheetFormDto(null, null, null, null, null, mapOf())
  val city = ComboBox<HotelCities>("City")
  val fromMonth = ComboBox<Month>("From month")
  val fromYear = ComboBox<Year>("From year")
  val toMonth = ComboBox<Month>("To month")
  val toYear = ComboBox<Year>("To year")
  val searchButton = Button("Search")
  val lastMonthButton = Button("Last month only")
  val grid = Grid(BalanceSheet::class.java)

  private fun configureGrid() {
    grid.setSizeFull()
    grid.setColumns("incomeSum", "outflowsSum", "closingBalance")
    grid.addColumn { balanceSheet: BalanceSheet ->
      balanceSheet.yearMonth.toString()
    }.setHeader("Year - Month")
    grid.addColumn { balanceSheet: BalanceSheet ->
      balanceSheet.incomes.joinToString(", ")
    }.setHeader("Incomes")
    grid.addColumn { balanceSheet: BalanceSheet ->
      balanceSheet.outflows.joinToString(", ")
    }.setHeader("Outflows")
    grid.columns.forEach(Consumer { col: Grid.Column<BalanceSheet?> -> col.setAutoWidth(true) })
    grid.setItems(balanceSheetApi.lastMonth())
  }

  init {
    configureGrid()
    setSizeFull()
    binder.withValidator(
      { p: BalanceSheetFormDto -> YearMonth.of(p.fromYear!!.value, p.fromMonth) < YearMonth.of(p.toYear!!.value, p.toMonth) },
      "From date must be before to date."
    )
    binder.bindInstanceFields(this)
    binder.bean = balanceSheetFormDto
    city.setItems(*HotelCities.values())
    fromMonth.setItems(*Month.values())
    fromYear.setItems(Year.of(2021))
    toMonth.setItems(*Month.values())
    toYear.setItems(Year.of(2021))
    val fromLayout = HorizontalLayout(fromMonth, fromYear)
    val toLayout = HorizontalLayout(toMonth, toYear)
    val buttonsLayout = HorizontalLayout(searchButton, lastMonthButton)
    searchButton.addClickListener { search() }
    lastMonthButton.addClickListener { grid.setItems(balanceSheetApi.lastMonth()) }
    val content = Div(city, fromLayout, toLayout, buttonsLayout)
    content.setSizeFull()
    add(content, grid)
  }

  private fun search() {
    try {
      println(balanceSheetFormDto)
      binder.writeBean(balanceSheetFormDto)
      val fromYm = YearMonth.of(balanceSheetFormDto.fromYear!!.value, balanceSheetFormDto.fromMonth)
      val toYm = YearMonth.of(balanceSheetFormDto.toYear!!.value, balanceSheetFormDto.toMonth)
      val r = balanceSheetApi.period(fromYm, toYm)
      println(r)
      grid.setItems(r)
    } catch (e: ValidationException) {
      fromMonth.errorMessage = e.fieldValidationErrors.joinToString { ", " }
    }
  }
}

data class BalanceSheetFormDto(
  var fromMonth: Month?,
  var fromYear: Year?,
  var toMonth: Month?,
  var toYear: Year?,
  var city: HotelCities?,
  var result: Map<YearMonth, BalanceSheet>
)

data class BalanceSheetDto(
  val incomeSum: BigDecimal,
  val outflowsSum: BigDecimal,
  val closingBalance: BigDecimal,
  val incomes: List<Income>,
  val outflows: List<Outflow>
)