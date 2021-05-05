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
import doobie.util._
import librame.domain.model._
import librame.domain.model.market._
import librame.domain.model.util._

import java.time.LocalDate

trait DoobieCustomColumnType {

  /** single value string */
  implicit def valueStringGet[T <: SingleValueObject[String]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    }
  implicit def valueStringPut[T <: SingleValueObject[String]]: Put[T]                            =
    Put[String].contramap(_.value)

  /** single value uuid */
  implicit def valueUUIDGet[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    }
  implicit def valueUUIDPut[T <: SingleValueObject[UUID]]: Put[T]                            =
    Put[String].contramap(_.value.toString)

  /** local date value object */
  implicit val localDateWrite: Write[LocalDate] =
    Write[(Int, Int, Int)].contramap(p => (p.getYear, p.getMonthValue, p.getDayOfMonth))

  /** market value object */
  implicit val moneyWrite: Write[Money] =
    Write[(BigDecimal, Currency)].contramap(p => (p.value, p.currency))

  implicit val priceWrite: Write[Price] =
    Write[(BigDecimal, Currency)].contramap(p => (p.money.value, p.money.currency))

  /** util string enum object */
  implicit def stringEnumPut[T <: StringEnum]: Put[T] =
    Put[String].contramap(_.code)

  /** util int enum object */
  implicit def intEnumPut[T <: IntEnum]: Put[T] =
    Put[Int].contramap(_.code)
}
