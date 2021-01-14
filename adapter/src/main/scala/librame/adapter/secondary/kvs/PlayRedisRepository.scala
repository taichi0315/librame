package librame.adapter.secondary.kvs

import java.util.UUID

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}
import scala.reflect.ClassTag

import play.api.cache.redis.CacheAsyncApi

import librame.domain.value.EntityId

class PlayRedisRepository[T <: EntityId : ClassTag](
  cache: CacheAsyncApi
) (implicit ec: ExecutionContext) 
extends CacheRepository[T] {

  def set(key: String, entityId: T, expire: Duration = Duration.Inf): Future[Unit] =
    for {
      _ <- cache.set(key, entityId.value.toString, expire)
    } yield ()

  def get(key: String): Future[Option[T]] =
    for {
      strOpt <- cache.get[String](key)
    } yield strOpt.map(convertStringToEntityId(_))

  def remove(key: String): Future[Unit] =
    for {
      _ <- cache.remove(key)
    } yield ()

  def convertStringToEntityId(str: String): T =
    implicitly[ClassTag[T]]
      .runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[T]
}
