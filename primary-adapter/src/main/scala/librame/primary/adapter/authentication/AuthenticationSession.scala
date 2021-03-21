package librame.primary.adapter.authentication

import play.api.mvc._

abstract class AuthenticationSession {

  val key: String
  val maxAge: Option[Int]

  /** @param value
    * @param result
    * @param rh
    * @return
    */
  def put(value: String)(result: Result)(implicit rh: RequestHeader): Result =
    result.withCookies(
      Cookie(key, value, maxAge)
    )

  /** @param rh
    * @return
    */
  def get(implicit rh: RequestHeader): Option[String] =
    rh.cookies.get(key).map(_.value)

  /** @param result
    * @param rh
    * @return
    */
  def discard(result: Result)(implicit rh: RequestHeader): Result =
    result.discardingCookies(
      DiscardingCookie(key)
    )
}
