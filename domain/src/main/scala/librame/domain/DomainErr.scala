package librame.domain

package object error {
  trait DomainValidation
  object DomainValidation extends DomainValidation

  sealed trait EmailAddressValidation extends DomainValidation
  object EmailAddressValidation extends EmailAddressValidation

  sealed trait PasswordValidation extends DomainValidation
  object PasswordValidation extends PasswordValidation
}
