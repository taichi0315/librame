package librame.primary.adapter.authentication

import java.util.UUID

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}

import play.api.libs.typedmap.TypedKey
import play.api.mvc.Results.Status
import play.api.mvc._

import librame.domain.model.EntityId
import librame.secondary.adapter.kvs.CacheRepository

/**
 * @param ec
 * @tparam T
 */
abstract class AuthenticationProfile[T <: EntityId]() (implicit ec: ExecutionContext) {

  object AttrKeys {
    val Auth: TypedKey[T] = TypedKey("Authentication")
  }

  val Unauthorized: Result = Status(401).apply("unauthorized")

  val sessionTimeout: Duration

  val sessionToken: AuthenticationSession

  val dataStore: CacheRepository[T]

  /**
   * @param entityId
   * @param result
   * @param rh
   * @return
   */
  def loginSucceed(entityId: T, result: Result)(implicit rh: RequestHeader): Future[Result] = {
    val token = UUID.randomUUID.toString
    for {
      _ <- dataStore.set(token, entityId, sessionTimeout)
    } yield
      sessionToken.put(token)(result)
  }

  /**
   * @param result
   * @param rh
   * @return
   */
  def logoutSucceed(result: Result)(implicit rh: RequestHeader): Future[Result] =
    for {
      _ <- sessionToken.get match {
        case Some(token) => dataStore.remove(token)
        case None        => Future.successful(())
      }
    } yield
      sessionToken.discard(result)

  /**
   * @param rh
   * @return
   */
  def authenticate(implicit rh: RequestHeader): Future[Either[Result, (T, Result => Result)]] =
    sessionToken.get match {
      case None        => Future.successful(Left(Unauthorized))
      case Some(token) =>
        for {
          entityIdOpt <- dataStore.get(token)
        } yield
          entityIdOpt match {
            case None           => Left(Unauthorized)
            case Some(entityId) => Right((entityId, sessionToken.put(token) _))
          }
    }
}
