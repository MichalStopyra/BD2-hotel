package pl.gr16.hotel.balancesheet

import java.math.BigDecimal
import java.time.YearMonth

interface BalanceSheetApi {
  fun lastMonth(): BalanceSheet
  fun period(from: YearMonth, to: YearMonth): List<BalanceSheet>
}

data class Income(val name: String, val value: BigDecimal) {
  override fun toString(): String {
    return "$name: $value PLN"
  }
}
data class Outflow(val name: String, val value: BigDecimal) {
  override fun toString(): String {
    return "$name: $value PLN"
  }
}
data class BalanceSheet(
  val yearMonth: YearMonth,
  val incomeSum: BigDecimal,
  val outflowsSum: BigDecimal,
  val incomes: List<Income>,
  val outflows: List<Outflow>
) {
  val closingBalance = incomeSum - outflowsSum
}