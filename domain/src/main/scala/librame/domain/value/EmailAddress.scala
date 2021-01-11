package librame.domain.value

case class EmailAddress(value: String) extends SingleValueObject[String]

object EmailAddress {
  def apply(value: String): Either[Unit, EmailAddress] =
    Right(value)
      .filterOrElse(_.matches(EMAIL_REGEX), ())
      .map(new EmailAddress(_))

  val EMAIL_REGEX = """(?:[-!#-'*+/-9=?A-Z^-~]+(?:\.[-!#-'*+/-9=?A-Z^-~]+)*|"(?:[!#-\[\]-~]|\\[\x09 -~])*")@[-!#-'*+/-9=?A-Z^-~]+(?:\.[-!#-'*+/-9=?A-Z^-~]+)*"""
}
