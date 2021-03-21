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

package librame.domain.model

import java.util.UUID

import scala.reflect.ClassTag

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}

object EntityId {

  /** @param tag
    * @tparam T
    * @return
    */
  def generate[T <: EntityId](implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.randomUUID)
      .asInstanceOf[T]

  /** @param str
    * @param tag
    * @tparam T
    * @return
    */
  def fromString[T <: EntityId](str: String)(implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[T]
}
