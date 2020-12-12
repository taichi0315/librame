package librame.adapter.primary.authentication

import play.api.mvc._

abstract class AuthenticationSession {

  val key:    String
  val maxAge: Option[Int]
  
  def put(value: String)(result: Result)(implicit rh: RequestHeader): Result =
    result.withCookies(
      Cookie(key, value, maxAge)
    )

  def get(implicit rh: RequestHeader): Option[String] =
    rh.cookies.get(key).map(_.value)

  def discard(result: Result)(implicit rh: RequestHeader): Result =
    result.discardingCookies(
      DiscardingCookie(key)
    )
}
