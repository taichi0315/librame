package librame.adapter.secondary.kvs

import java.util.UUID

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}
import scala.reflect.ClassTag

import play.api.cache.redis.CacheAsyncApi

import librame.domain.model.EntityId

/**
 * @param cache
 * @param classTag$T$0
 * @param ec
 * @tparam T
 */
class PlayRedisRepository[T <: EntityId : ClassTag](
  cache: CacheAsyncApi
) (implicit ec: ExecutionContext) 
extends CacheRepository[T] {

  /**
   * @param key
   * @param entityId
   * @param expire
   * @return
   */
  def set(key: String, entityId: T, expire: Duration = Duration.Inf): Future[Unit] =
    for {
      _ <- cache.set(key, entityId.value.toString, expire)
    } yield ()

  /**
   * @param key
   * @return
   */
  def get(key: String): Future[Option[T]] =
    for {
      strOpt <- cache.get[String](key)
    } yield strOpt.map(convertStringToEntityId(_))

  /**
   * @param key
   * @return
   */
  def remove(key: String): Future[Unit] =
    for {
      _ <- cache.remove(key)
    } yield ()

  /**
   * @param str
   * @return
   */
  def convertStringToEntityId(str: String): T =
    implicitly[ClassTag[T]]
      .runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[T]
}
