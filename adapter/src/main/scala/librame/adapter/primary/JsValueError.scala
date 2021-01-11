package librame.adapter.primary

import play.api.libs.json._

case class JsValueError(
  errorCode: String,
  target:    String
)

object JsValueError {
  implicit val errorWrites: Writes[JsValueError] = Json.writes[JsValueError]
}
