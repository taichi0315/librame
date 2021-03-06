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

package librame.secondary.adapter.rdb

import java.util.UUID

import scala.reflect.ClassTag

import slick.jdbc.{SetParameter, GetResult}

import librame.domain.model.SingleValueObject

trait SlickCustomColumnType {

  /** single value string */
  implicit val valueStringSet = SetParameter[SingleValueObject[String]] { case (vo, params) =>
    params.setString(vo.value)
  }

  implicit def valueStringGet[T <: SingleValueObject[String]](implicit
    tag: ClassTag[T]
  ): GetResult[T] =
    GetResult[T] { r =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(r.<<[String])
        .asInstanceOf[T]
    }

  /** single value uuid */
  implicit val valueUUIDSet = SetParameter[SingleValueObject[UUID]] { case (vo, params) =>
    params.setString(vo.value.toString)
  }

  implicit def valueUUIDGet[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]): GetResult[T] =
    GetResult[T] { r =>
      tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(r.<<[String]))
        .asInstanceOf[T]
    }
}
