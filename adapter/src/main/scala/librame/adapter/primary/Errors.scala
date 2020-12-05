package librame.adapter.primary

import play.api.mvc.Results._

trait Errors {
  def E_NOT_FOUND      (str: String) = NotFound(s"not found $str")
  def E_BAD_REQUEST    (str: String) = BadRequest(s"bad request $str")
  def E_VALIDATE       (str: String) = BadRequest(s"invalid $str")
  def E_DUPLICATE      (str: String) = BadRequest(s"duplicate entry $str")
  def E_INTERNAL_SERVER(str: String) = InternalServerError(s"internal server in $str")
  def E_AUTHENTICATION (str: String) = Unauthorized(s"autentication failture of $str")
  def E_AUTHORIZATION  (str: String) = Unauthorized(s"authorization failture of $str")
}

object Errors extends Errors
