package librame.domain.value

import librame.domain.error.EmailAddressValidation

case class EmailAddress(value: String) extends Value[String]

object EmailAddress {
  
  def apply(email: String): Either[EmailAddressValidation, EmailAddress] =
    Either.cond(
      email.matches("""[a-z0-9]+@[a-z0-9]+\.[a-z0-9]{2,}"""),
      new EmailAddress(email),
      EmailAddressValidation
    )
}
