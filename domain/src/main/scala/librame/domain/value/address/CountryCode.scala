package librame.domain.value.address

import librame.domain.value.SingleValueObject

case class CountryCode(value: String) extends SingleValueObject[String]

object CountryCode {
  def apply(value: String): Either[Unit, CountryCode] =
    Right(value)
      .filterOrElse(_.matches(COUNTRY_CODE_REGEX), ())
      .map(new CountryCode(_))

  val COUNTRY_CODE_REGEX = """[0-9\-]+"""
}
