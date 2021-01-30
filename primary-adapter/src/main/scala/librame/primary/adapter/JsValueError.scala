package librame.primary.adapter

import play.api.libs.json._

/**
 * @param errorCode
 * @param target
 */
case class JsValueError(
  errorCode: String,
  target:    String
)

object JsValueError {
  implicit val errorWrites: Writes[JsValueError] = Json.writes[JsValueError]
}
