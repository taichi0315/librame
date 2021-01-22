package librame.domain.model.address

import librame.domain.error.DomainErr

/**
 * Address Value Object
 * @param countryCode
 * @param zipCode
 * @param city
 * @param address1
 * @param address2
 */
case class Address(
  countryCode: CountryCode,
  zipCode:     ZipCode,
  city:        String,
  address1:    String,
  address2:    String
)

object Address {
  /**
   * Constructor
   * @param rawCountryCode
   * @param rawZipCode
   * @param city
   * @param address1
   * @param address2
   * @return
   */
  def apply(
    rawCountryCode: String,
    rawZipCode:     String,
    city:           String,
    address1:       String,
    address2:       String
  ): Either[DomainErr, Address] =
    for {
      countryCode <- CountryCode(rawCountryCode).left.map(_ => CountryCodeValidateErr)
      zipCode     <- ZipCode(rawZipCode).left.map(_ => ZipCodeValidateErr)
    } yield
      new Address(countryCode, zipCode, city, address1, address2)

  case object CountryCodeValidateErr extends DomainErr
  case object ZipCodeValidateErr     extends DomainErr
}
