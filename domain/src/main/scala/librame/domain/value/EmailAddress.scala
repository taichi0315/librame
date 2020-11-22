package ddd.lib.domain.value

import ddd.lib.domain.error.EmailAddressValidation

case class EmailAddress(value: String) extends Value[String]

object EmailAddress {
  
  def apply(email: String): Either[EmailAddressValidation, EmailAddress] =
    Either.cond(
      email.matches("""[a-z0-9]+@[a-z0-9]+\.[a-z0-9]{2,}"""),
      new EmailAddress(email),
      EmailAddressValidation
    )
}
