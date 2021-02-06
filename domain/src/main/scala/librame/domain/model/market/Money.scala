package librame.domain.model.market

import scala.math.BigDecimal

import cats.Monoid

/**
 * @param value
 * @param currency
 */
case class Money(
  value:    BigDecimal,
  currency: Currency
) {
  /** 不変条件 */
  assert(value >= 0, "金額が0以上")

  /**
   * @param that
   * @return
   */
  def +(that: Money): Money = plus(that)
  def plus(that: Money): Money =
    that.currency match {
      case this.currency => this.copy(value = this.value + that.value)
      case _             => throw new UnsupportedOperationException("plus not supported for cross-currency comparison")
    }

  /**
   * @param that
   * @return
   */
  def -(that: Money): Money = minus(that)
  def minus(that: Money): Money = {
    that.currency match {
      case this.currency => this.copy(value = this.value - that.value)
      case _             => throw new UnsupportedOperationException("minus not supported for cross-currency comparison")
    }
  } ensuring(_.value >= 0, "金額が0以上")

  /**
   * @param factor
   * @return
   */
  def *(factor: BigDecimal): Money = mul(factor)
  def mul(factor: BigDecimal): Money = {
    require(factor >= 0, "掛ける値は0以上")
    this.copy(value = this.value * factor)
  }

  /**
   * @param factor
   * @return
   */
  def /(factor: BigDecimal): Money = div(factor)
  def div(factor: BigDecimal): Money = {
    require(factor > 0, "割る値は0より大きい")
    this.copy(value = this.value / factor)
  }
}

object Money {
  /**
   * @param value
   * @param currency
   * @return
   */
  def apply(value: BigDecimal, currency: Currency = Currency.JPY): Either[Unit, Money] =
    Right(value)
      .filterOrElse(_ >= 0, ())
      .map(v => new Money(v, currency))

  /** implict value for Monoid */
  implicit val moneyMonoid: Monoid[Money] = new Monoid[Money] {
    def empty: Money = new Money(0, Currency.JPY)

    def combine(x: Money, y: Money): Money = x + y
  }
}
