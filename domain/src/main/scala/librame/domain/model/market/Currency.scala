package librame.domain.model.market

import librame.domain.model.util.StringEnum

/** @param code
  */
abstract class Currency(val code: String) extends StringEnum

object Currency extends StringEnum.Of[Currency] {
  case object JPY extends Currency("JPY")
  case object USD extends Currency("USD")

  val values: Seq[Currency] = Seq(JPY, USD)
}
