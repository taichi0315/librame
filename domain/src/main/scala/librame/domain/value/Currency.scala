package librame.domain.value

case class Currency(value: String) extends SingleValueObject[String]

object Currency {
  def apply(rawCurrency: String): Either[Unit, Currency] =
    Right(rawCurrency)
      .filterOrElse(isValidate(_), ())
      .map(new Currency(_))

  private def isValidate(rawCurrency: String): Boolean =
    rawCurrency.length == 3 &&
    rawCurrency.forall(_.isUpper)
}
