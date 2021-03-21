package librame.secondary.adapter.kvs

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContext}
import scala.reflect.ClassTag

import play.api.cache.redis.CacheAsyncApi

import librame.domain.model.EntityId

/** @param cache
  * @param ec
  * @param tag
  * @tparam T
  */
class PlayRedisRepository[T <: EntityId](
  cache: CacheAsyncApi
)(implicit
  ec: ExecutionContext,
  tag: ClassTag[T]
) extends CacheRepository[T] {

  /** @param key
    * @param entityId
    * @param expire
    * @return
    */
  def set(key: String, entityId: T, expire: Duration = Duration.Inf): Future[Unit] =
    for {
      _ <- cache.set(key, entityId.value.toString, expire)
    } yield ()

  /** @param key
    * @return
    */
  def get(key: String): Future[Option[T]] =
    for {
      strOpt <- cache.get[String](key)
    } yield strOpt.map(EntityId.fromString[T](_))

  /** @param key
    * @return
    */
  def remove(key: String): Future[Unit] =
    for {
      _ <- cache.remove(key)
    } yield ()
}
