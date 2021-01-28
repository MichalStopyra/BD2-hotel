package pl.gr16.hotel.balancesheet

import org.springframework.stereotype.Component
import java.time.Clock
import java.time.YearMonth

@Component
class BalanceSheetFacade(
  private val clock: Clock,
  private val balanceSheetRepository: BalanceSheetRepository
) : BalanceSheetApi {

  override fun lastMonth(): BalanceSheet {
    return balanceSheetRepository.forMonth(YearMonth.now(clock))
  }

  override fun period(from: YearMonth, to: YearMonth): List<BalanceSheet> {
    if (to.isBefore(from)) return listOf()
    val sheets = mutableMapOf<YearMonth, BalanceSheet>()
    var ym = from
    val firstNotIncludedMonth = to.plusMonths(1)
    while (ym != firstNotIncludedMonth) {
      val sheet = balanceSheetRepository.forMonth(ym)
      sheets.put(ym, sheet)
      ym = ym.plusMonths(1)
    }
    return sheets.values.toList()
  }
}
