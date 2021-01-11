package librame.domain.value.market

abstract class Currency(val code: String)

object Currency {
  case object JPY extends Currency("JPY")
  case object USD extends Currency("USD")
}
