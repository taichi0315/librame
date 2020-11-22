package ddd.lib.adapter.secondary.kvs

import scala.concurrent.Future
import scala.concurrent.duration.Duration

import ddd.lib.domain.model._

trait CacheRepository[T <: EntityId] {

  def set(key: String, entityId: T, expire: Duration = Duration.Inf): Future[Unit]

  def get(key: String): Future[Option[T]]

  def remove(key: String): Future[Unit]
}
