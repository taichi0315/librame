package librame.domain.model

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptPasswordHasher

import librame.domain.error.DomainErr

/**
 * Hashed Password Value Object
 * @param value
 */
case class HashedPassword(value: String) extends SingleValueObject[String] {
  import HashedPassword.passwordHasher


  def verify(rawPassword: String): Boolean =
    passwordHasher.matches(new PasswordInfo("bcrypt", value), rawPassword)
}

object HashedPassword {

  lazy val passwordHasher = new BCryptPasswordHasher()

  /**
   * Constructor
   * @param value
   * @return
   */
  def apply(value: String): Either[DomainErr, HashedPassword] =
    Right(value)
      .filterOrElse(_.length >= 8, LengthErr)
      .filterOrElse(_.matches(PASSWORD_REGEX), RegexErr)
      .map(v => new HashedPassword(passwordHasher.hash(v).password))

  val PASSWORD_REGEX = """[-_@+*;:#$%&A-Za-z0-9]+"""

  case object LengthErr extends DomainErr
  case object RegexErr  extends DomainErr
}
