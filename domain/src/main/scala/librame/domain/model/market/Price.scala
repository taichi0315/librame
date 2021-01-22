package librame.domain.model.market

/**
 * Price Value Object
 * @param money
 */
case class Price(
  money: Money
) {
  val SALES_TAX_RATE = 0.1

  /**
   * calculate tax
   */
  lazy val tax: Money = money * SALES_TAX_RATE
}
