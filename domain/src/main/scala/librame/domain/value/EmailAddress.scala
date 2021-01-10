package librame.domain.value

case class EmailAddress(value: String) extends SingleValueObject[String]

object EmailAddress {
  def apply(rawEmail: String): Either[Unit, EmailAddress] =
    Right(rawEmail)
      .filterOrElse(isValidate(_), ())
      .map(new EmailAddress(_))

  private def isValidate(rawEmail: String): Boolean =
    rawEmail.matches("""[a-z0-9]+@[a-z0-9]+\.[a-z0-9]{2,}""")
}
