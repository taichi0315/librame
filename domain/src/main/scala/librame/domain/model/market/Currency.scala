package librame.domain.model.market

/**
 * @param code
 */
abstract class Currency(val code: String)

object Currency {
  case object JPY extends Currency("JPY")
  case object USD extends Currency("USD")
}
