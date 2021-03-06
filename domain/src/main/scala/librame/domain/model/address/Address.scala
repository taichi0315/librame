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
