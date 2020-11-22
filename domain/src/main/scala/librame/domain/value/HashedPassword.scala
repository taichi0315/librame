package ddd.lib.domain.value

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptPasswordHasher

import ddd.lib.domain.error.PasswordValidation

case class HashedPassword(value: PasswordInfo) extends Value[PasswordInfo] {
  import HashedPassword.passwordHasher


  def verify(rawPassword: String): Boolean =
    passwordHasher.matches(value, rawPassword)
}

object HashedPassword {

  lazy val passwordHasher = new BCryptPasswordHasher()

  def apply(password: String): Either[PasswordValidation, HashedPassword] =
    Either.cond(
      password.length >= 8,
      new HashedPassword(passwordHasher.hash(password)),
      PasswordValidation
    )
}
