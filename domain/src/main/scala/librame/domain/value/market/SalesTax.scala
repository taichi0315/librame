package librame.domain.value.market

case class SalesTax(
  money: Money
) {
  val SALES_TAX_RATE = 0.1

  lazy val calculate: Money = money * SALES_TAX_RATE
}
