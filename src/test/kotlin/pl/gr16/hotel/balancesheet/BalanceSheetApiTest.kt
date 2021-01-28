package pl.gr16.hotel.balancesheet

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.maps.shouldContainValue
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.Clock
import java.time.YearMonth

class BalanceSheetApiTest : StringSpec({
  val clock = Clock.systemDefaultZone()
  val balanceSheetApi = BalanceSheetFacade(clock)

  "should return sheet for last month" {
    val sheet = balanceSheetApi.lastMonth()
    sheet.closingBalance shouldBe BigDecimal(5)
  }

  "should return sheet for january to march 2020" {
    val jan2020 = YearMonth.of(2020, 1)
    val sheets = balanceSheetApi.period(jan2020, jan2020.plusMonths(2))
    sheets shouldContainKey jan2020
    sheets shouldContainKey jan2020.plusMonths(1)
    sheets shouldContainKey jan2020.plusMonths(2)

    sheets.get(jan2020)!!.closingBalance shouldBe BigDecimal(5)
  }
})
