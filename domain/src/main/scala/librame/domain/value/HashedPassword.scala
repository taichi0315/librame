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

  def apply(value: String): Either[Unit, HashedPassword] =
    Right(value)
      .filterOrElse(_.length >= 8, ())
      .map(v => new HashedPassword(passwordHasher.hash(v).password))
}
