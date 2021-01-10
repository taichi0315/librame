package librame.domain.value

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptPasswordHasher

case class HashedPassword(value: String) extends SingleValueObject[String] {
  import HashedPassword.passwordHasher


  def verify(rawPassword: String): Boolean =
    passwordHasher.matches(new PasswordInfo("bcrypt", value), rawPassword)
}

object HashedPassword {

  lazy val passwordHasher = new BCryptPasswordHasher()

  def apply(rawPassword: String): Either[Unit, HashedPassword] =
    Right(rawPassword)
      .filterOrElse(isValidate(_), ())
      .map(raw => new HashedPassword(passwordHasher.hash(raw).password))

  private def isValidate(password: String): Boolean =
    password.length >= 8
}
