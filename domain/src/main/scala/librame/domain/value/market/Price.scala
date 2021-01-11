package librame.domain.value.market

case class Price(
  money: Money
) {
  val SALES_TAX_RATE = 0.1

  lazy val tax: Money = money * SALES_TAX_RATE
}
