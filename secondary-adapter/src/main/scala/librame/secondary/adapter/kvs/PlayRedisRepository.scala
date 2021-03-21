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
