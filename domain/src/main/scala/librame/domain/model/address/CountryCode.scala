package librame.domain.model.address

import librame.domain.model.SingleValueObject

/**
 * Country Code Value Object
 *
 * @param value
 */
case class CountryCode(value: String) extends SingleValueObject[String]

object CountryCode {
  /**
   * Constructor
   *
   * @param value
   * @return
   */
  def apply(value: String): Either[Unit, CountryCode] =
    Right(value)
      .filterOrElse(_.matches(COUNTRY_CODE_REGEX), ())
      .map(new CountryCode(_))

  val COUNTRY_CODE_REGEX = """[0-9\-]+"""
}
