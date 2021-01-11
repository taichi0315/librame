package librame.adapter.primary

import play.api.mvc.Result
import play.api.mvc.Results._
import play.api.libs.json._

case class Error(status: Status, errorCode: String, target: String) {
  def toResult: Result = status(Json.toJson(JsValueError(errorCode, target)))
}

trait Errors {
  def E_NOT_FOUND      (target: String) = Error(NotFound,            "not-found",              target)
  def E_BAD_REQUEST    (target: String) = Error(BadRequest,          "bad-request",            target)
  def E_VALIDATION     (target: String) = Error(BadRequest,          "invalid-value",          target)
  def E_DUPLICATION    (target: String) = Error(BadRequest,          "duplicate-entry",        target)
  def E_INTERNAL_SERVER(target: String) = Error(InternalServerError, "internal-server",        target)
  def E_AUTHENTICATION (target: String) = Error(Unauthorized,        "autentication-failture", target)
  def E_AUTHORIZATION  (target: String) = Error(Unauthorized,        "authorization-failture", target)
}

object Errors extends Errors
