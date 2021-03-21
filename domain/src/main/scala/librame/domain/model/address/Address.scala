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

package librame.domain.model.address

import librame.domain.error.DomainErr

/** @param countryCode
  * @param zipCode
  * @param city
  * @param address1
  * @param address2
  */
case class Address(
  countryCode: CountryCode,
  zipCode: ZipCode,
  city: String,
  address1: String,
  address2: String
)

object Address {

  /** @param countryCodeValue
    * @param zipCodeValue
    * @param city
    * @param address1
    * @param address2
    * @return
    */
  def apply(
    countryCodeValue: String,
    zipCodeValue: String,
    city: String,
    address1: String,
    address2: String
  ): Either[DomainErr, Address] =
    for {
      countryCode <- CountryCode(countryCodeValue).left.map(_ => CountryCodeValidateErr)
      zipCode     <- ZipCode(zipCodeValue).left.map(_ => ZipCodeValidateErr)
    } yield new Address(countryCode, zipCode, city, address1, address2)

  case object CountryCodeValidateErr extends DomainErr
  case object ZipCodeValidateErr     extends DomainErr
}
