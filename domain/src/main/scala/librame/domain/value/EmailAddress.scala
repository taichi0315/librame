package librame.domain.value

case class EmailAddress(value: String) extends Value[String]

object EmailAddress {

  sealed trait ValidateErr
  case object ValidateErr extends ValidateErr
  
  def apply(rawEmail: String): Either[ValidateErr, EmailAddress] =
    Right(rawEmail)
      .filterOrElse(isValidate(_), ValidateErr)
      .map(new EmailAddress(_))

  private def isValidate(rawEmail: String): Boolean =
    rawEmail.matches("""[a-z0-9]+@[a-z0-9]+\.[a-z0-9]{2,}""")
}
