package librame.domain.value.market

import scala.math.BigDecimal

case class Money(
  value:    BigDecimal,
  currency: Currency
) {
  def +(that: Money): Money = plus(that)
  def plus(that: Money): Money =
    that.currency match {
      case this.currency => this.copy(value = this.value + that.value)
      case _             => throw new UnsupportedOperationException("plus not supported for cross-currency comparison")
    }

  def -(that: Money): Money = minus(that)
  def minus(that: Money): Money = {
    that.currency match {
      case this.currency => this.copy(value = this.value - that.value)
      case _             => throw new UnsupportedOperationException("minus not supported for cross-currency comparison")
    }
  } ensuring(_.value >= 0, "金額が0以上")

  def *(factor: BigDecimal): Money = mul(factor)
  def mul(factor: BigDecimal): Money = {
    require(factor >= 0, "掛ける値は0以上")
    this.copy(value = this.value * factor)
  }

  def /(factor: BigDecimal): Money = div(factor)
  def div(factor: BigDecimal): Money = {
    require(factor > 0, "割る値は0より大きい")
    this.copy(value = this.value / factor)
  }
}

object Money {
  def apply(value: BigDecimal, currency: Currency = Currency.JPY): Either[Unit, Money] =
    Right(value)
      .filterOrElse(_ >= 0, ())
      .map(v => new Money(v, currency))
}
