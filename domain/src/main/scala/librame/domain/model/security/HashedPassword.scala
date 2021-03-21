/*
 * Copyright (c) 2021 Kushiro Taichi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package librame.domain.model.security

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptPasswordHasher

import librame.domain.error.DomainErr
import librame.domain.model.SingleValueObject

/** @param value
  */
case class HashedPassword(value: String) extends SingleValueObject[String] {

  import HashedPassword.passwordHasher

  /** @param passwordValue
    * @return
    */
  def verify(passwordValue: String): Boolean =
    passwordHasher.matches(new PasswordInfo("bcrypt", value), passwordValue)
}

object HashedPassword {

  lazy val passwordHasher = new BCryptPasswordHasher()

  /** @param value
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
