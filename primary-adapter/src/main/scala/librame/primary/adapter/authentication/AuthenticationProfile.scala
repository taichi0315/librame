/*
 * Copyright (c) 2021 Kushiro Taichi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package librame.primary.adapter.authentication

import java.util.UUID

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}

import play.api.libs.typedmap.TypedKey
import play.api.mvc.Results.Status
import play.api.mvc._

import librame.domain.model.EntityId
import librame.secondary.adapter.kvs.CacheRepository

/** @param ec
  * @tparam T
  */
abstract class AuthenticationProfile[T <: EntityId]()(implicit ec: ExecutionContext) {

  object AttrKeys {
    val Auth: TypedKey[T] = TypedKey("Authentication")
  }

  val Unauthorized: Result = Status(401).apply("unauthorized")

  val sessionTimeout: Duration

  val sessionToken: AuthenticationSession

  val dataStore: CacheRepository[T]

  /** @param entityId
    * @param result
    * @param rh
    * @return
    */
  def loginSucceed(entityId: T, result: Result)(implicit rh: RequestHeader): Future[Result] = {
    val token = UUID.randomUUID.toString
    for {
      _ <- dataStore.set(token, entityId, sessionTimeout)
    } yield sessionToken.put(token)(result)
  }

  /** @param result
    * @param rh
    * @return
    */
  def logoutSucceed(result: Result)(implicit rh: RequestHeader): Future[Result] =
    for {
      _ <- sessionToken.get match {
        case Some(token) => dataStore.remove(token)
        case None        => Future.successful(())
      }
    } yield sessionToken.discard(result)

  /** @param rh
    * @return
    */
  def authenticate(implicit rh: RequestHeader): Future[Either[Result, (T, Result => Result)]] =
    sessionToken.get match {
      case None        => Future.successful(Left(Unauthorized))
      case Some(token) =>
        for {
          entityIdOpt <- dataStore.get(token)
        } yield entityIdOpt match {
          case None           => Left(Unauthorized)
          case Some(entityId) => Right((entityId, sessionToken.put(token) _))
        }
    }
}
