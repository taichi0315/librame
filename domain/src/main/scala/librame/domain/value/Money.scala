package librame.domain.value

case class Money(
  currency: Currency,
  value:    BigDecimal
)
