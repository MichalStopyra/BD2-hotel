package pl.gr16.hotel.balancesheet

import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.sql.ResultSet
import java.time.YearMonth

@Repository
class BalanceSheetRepository {
  fun forMonth(ym: YearMonth): BalanceSheet {
    val incomes = incomes(ym)
    val outflows = outflows(ym)
    BalanceSheet(
      ym,
      incomes.map { it.value }.fold(BigDecimal.ZERO, BigDecimal::add),
      outflows.map { it.value }.fold(BigDecimal.ZERO, BigDecimal::add),
      incomes,
      outflows
    )
    return sheet1(ym)
  }

  private fun incomes(ym: YearMonth): List<Income> {
    val from = "{$ym.year}-${ym.month.value}-01"
    val to = "{$ym.year}-${ym.month.value}-${ym.lengthOfMonth()}"
    val query =
      """
        select * from Supply s 
        where s.date_when BETWEEN $from AND $to
      """
    return query.execAndMap { rs ->
      Income(
        rs.getString("name"),
        rs.getBigDecimal("profit")
      )
    }
  }

  private fun outflows(ym: YearMonth): List<Outflow> {
    val from = "{$ym.year}-${ym.month.value}-01"
    val to = "{$ym.year}-${ym.month.value}-${ym.lengthOfMonth()}"
    val query =
      """
        select * from Task t
        where t.date_when BETWEEN $from AND $to
      """
    return query.execAndMap { rs ->
      Outflow(
        rs.getString("name"),
        rs.getBigDecimal("outflow")
      )
    }
  }

    private fun sheet1(ym: YearMonth): BalanceSheet {
    val incomes = listOf(
      Income("stays", BigDecimal(6)),
      Income("supplies", BigDecimal(4)),
    )
    val outflows = listOf(
      Outflow("rent", BigDecimal(-3)),
      Outflow("fixes", BigDecimal(-2)),
    )
    return BalanceSheet(
      ym,
      BigDecimal.TEN,
      BigDecimal(5),
      incomes,
      outflows
    )
  }

  fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
    val result = arrayListOf<T>()
    TransactionManager.current().exec(this) { rs ->
      while (rs.next()) {
        result += transform(rs)
      }
    }
    return result
  }
}