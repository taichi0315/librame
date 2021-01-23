package librame.adapter.secondary.kvs

import scala.concurrent.Future
import scala.concurrent.duration.Duration

import librame.domain.model.EntityId

/**
 * @tparam T
 */
trait CacheRepository[T <: EntityId] {

  /**
   * @param key
   * @param entityId
   * @param expire
   * @return
   */
  def set(key: String, entityId: T, expire: Duration = Duration.Inf): Future[Unit]

  /**
   * @param key
   * @return
   */
  def get(key: String): Future[Option[T]]

  /**
   * @param key
   * @return
   */
  def remove(key: String): Future[Unit]
}
