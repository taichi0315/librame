package librame.domain.value

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptPasswordHasher

import librame.domain.error._

case class HashedPassword(value: String) extends SingleValueObject[String] {
  import HashedPassword.passwordHasher


  def verify(rawPassword: String): Boolean =
    passwordHasher.matches(new PasswordInfo("bcrypt", value), rawPassword)
}

object HashedPassword {

  lazy val passwordHasher = new BCryptPasswordHasher()

  sealed trait ValidateErr extends DomainErr
  case object ValidateErr extends ValidateErr

  def apply(rawPassword: String): Either[ValidateErr, HashedPassword] =
    Right(rawPassword)
      .filterOrElse(isValidate(_), ValidateErr)
      .map(raw => new HashedPassword(passwordHasher.hash(raw).password))

  private def isValidate(password: String): Boolean =
    password.length >= 8
}
