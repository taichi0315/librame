package librame.domain.value


case class EmailAddress(value: String) extends SingleValueObject[String]

object EmailAddress {
  def apply(rawEmail: String): Either[Unit, EmailAddress] =
    Right(rawEmail)
      .filterOrElse(isValidate(_), ())
      .map(new EmailAddress(_))

  private def isValidate(rawEmail: String): Boolean =
    rawEmail.matches(EMAIL_REGEXP)

  val EMAIL_REGEXP = """(?:[-!#-'*+/-9=?A-Z^-~]+(?:\.[-!#-'*+/-9=?A-Z^-~]+)*|"(?:[!#-\[\]-~]|\\[\x09 -~])*")@[-!#-'*+/-9=?A-Z^-~]+(?:\.[-!#-'*+/-9=?A-Z^-~]+)*"""
}
