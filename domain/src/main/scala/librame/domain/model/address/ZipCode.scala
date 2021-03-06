package librame.domain.model.address

import librame.domain.model.SingleValueObject

/** @param value
  */
case class ZipCode(value: String) extends SingleValueObject[String]

object ZipCode {

  /** @param value
    * @return
    */
  def apply(value: String): Either[Unit, ZipCode] =
    Right(value)
      .filterOrElse(_.matches(ZIP_CODE_REGEX), ())
      .map(new ZipCode(_))

  val ZIP_CODE_REGEX = """[0-9]{3}-?[0-9]{4}"""
}
