package librame.domain.model.market

/**
 * @param money
 */
case class Price(
  money: Money
) {
  val SALES_TAX_RATE = 0.1

  lazy val tax: Money = money * SALES_TAX_RATE

  def +(that: Price): Price = plus(that)
  def plus(that: Price): Price = Price(this.money + that.money)
  def -(that: Price): Price = minus(that)
  def minus(that: Price): Price = Price(this.money - that.money)
  def *(factor: BigDecimal): Price = mul(factor)
  def mul(factor: BigDecimal): Price = Price(this.money * factor)
  def /(factor: BigDecimal): Price = div(factor)
  def div(factor: BigDecimal): Price = Price(this.money / factor)
}

object Price {
  /**
   * @param value
   * @param currency
   * @return
   */
  def apply(value: BigDecimal, currency: Currency = Currency.JPY): Either[Unit, Price] =
    Money(value, currency)
      .map(new Price(_))
}
