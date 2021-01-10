package librame.domain.value

case class Currency(value: String) extends SingleValueObject[String]

object Currency {
  def apply(value: String): Either[Unit, Currency] =
    Right(value)
      .filterOrElse(isValidate(_), ())
      .map(new Currency(_))

  private def isValidate(value: String): Boolean =
    value.length == 3 &&
    value.forall(_.isUpper)
}
