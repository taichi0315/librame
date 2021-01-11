package librame.domain.value

import scala.math.BigDecimal

import librame.domain.error.DomainErr

case class Money(
  value:    BigDecimal,
  currency: Currency
) {
  def plus(other: Money): Money =
    this.copy(value = this.value + other.value)

  def minus(other: Money): Money = {
    this.copy(value = this.value - other.value)
  } ensuring(_.value >= 0, "金額が0以上")

  def mul(factor: BigDecimal): Money = {
    require(factor >= 0, "掛ける値は0以上")
    this.copy(value = this.value * factor)
  }

  def div(factor: BigDecimal): Money = {
    require(factor > 0, "割る値は0より大きい")
    this.copy(value = this.value / factor)
  }
}

object Money {
  def apply(value: BigDecimal, rawCurrency: String = "JPY"): Either[DomainErr, Money] =
    for { 
      currency <- Currency(rawCurrency).left.map(_ => CurrencyValidateErr)
      money    <- Right(value)
        .filterOrElse(isValidate(_), MoneyValidateErr)
        .map(v => new Money(v, currency))
    } yield money

  private def isValidate(value: BigDecimal): Boolean = value >= 0

  case object CurrencyValidateErr extends DomainErr
  case object MoneyValidateErr    extends DomainErr
}
