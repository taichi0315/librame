package librame.primary.adapter

import play.api.libs.json.{ Reads, JsSuccess, JsError }
import play.api.mvc.Results.BadRequest
import play.api.mvc._

trait JsonHelper {
  /**
   * @param request
   * @param reads
   * @tparam T
   * @return
   */
  def bindFromRequest[T](implicit request: Request[AnyContent], reads: Reads[T]): Either[Result, T] =
    for {
      json   <- request.body.asJson.toRight(BadRequest)
      result <- json.validate[T] match {
        case JsSuccess(v, _) => Right(v)
        case JsError(e) => Left(BadRequest(JsError.toJson(e)))
      }
    } yield result
}

object JsonHelper extends JsonHelper
