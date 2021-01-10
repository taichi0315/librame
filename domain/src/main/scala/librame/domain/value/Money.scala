package librame.domain.value

import scala.math.BigDecimal

case class Money(
  value:    BigDecimal,
  currency: Currency
)

object Money {
  def apply(value: BigDecimal, rawCurrency: String = "JPY"): Either[Unit, Money] =
    for {
      currency <- Currency(rawCurrency)
      money    <- Right(value)
        .filterOrElse(isValidate(_), ())
        .map(v => new Money(v, currency))
    } yield money

  private def isValidate(value: BigDecimal): Boolean = value > 0
}
