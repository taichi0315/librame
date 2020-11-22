package librame.adapter.primary.auth

import java.util.UUID

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}

import play.api.libs.typedmap.TypedKey
import play.api.mvc.Results.Status
import play.api.mvc._

import librame.adapter.secondary.kvs.CacheRepository
import librame.domain.model._

abstract class AuthProfile[T <: EntityId]() (implicit ec: ExecutionContext) {

  object AttrKeys {
    val Auth: TypedKey[T] = TypedKey("Authentication")
  }

  val Unauthorized: Result = Status(401).apply("unauthorized user")

  val sessionTimeout: Duration

  val sessionToken: AuthSession

  val dataStore: CacheRepository[T]
  
  def loginSucceed(entityId: T, result: Result)(implicit rh: RequestHeader): Future[Result] = {
    val token = UUID.randomUUID.toString
    for {
      _ <- dataStore.set(token, entityId, sessionTimeout)
    } yield
      sessionToken.put(token)(result)
  }

  def logoutSucceed(result: Result)(implicit rh: RequestHeader): Future[Result] =
    for {
      _ <- sessionToken.get match {
        case Some(token) => dataStore.remove(token)
        case None        => Future.successful(())
      }
    } yield
      sessionToken.discard(result)

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
