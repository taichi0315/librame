package librame.secondary.adapter.rdb

import java.util.UUID

import scala.reflect.ClassTag

import doobie.util._

import librame.domain.model._
import librame.domain.model.market._
import librame.domain.model.util._

trait DoobieCustomColumnType {

  /** single value string */
  implicit def valueStringGet[T <: SingleValueObject[String]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    }
  implicit def valueStringPut[T <: SingleValueObject[String]]: Put[T] =
    Put[String].contramap(_.value)

  /** single value uuid */
  implicit def valueUUIDGet[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    }
  implicit def valueUUIDPut[T <: SingleValueObject[UUID]]: Put[T] =
    Put[String].contramap(_.value.toString)

  /** market value object */
  implicit val moneyWrite: Write[Money] =
    Write[(BigDecimal, Currency)].contramap(p => (p.value, p.currency))

  implicit val priceWrite: Write[Price] =
    Write[(BigDecimal, Currency)].contramap(p => (p.money.value, p.money.currency))

  /** util string enum object */
  implicit def stringEnumGet[T <: StringEnum](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
  }
  implicit def stringEnumPut[T <: StringEnum]: Put[T] =
    Put[String].contramap(_.code)

  /** util int enum object */
  implicit def intEnumGet[T <: IntEnum](implicit tag: ClassTag[T]): Get[T] =
    Get[Int].map { int =>
      tag.runtimeClass
        .getConstructor(classOf[Int])
        .newInstance(int)
        .asInstanceOf[T]
  }
  implicit def intEnumPut[T <: IntEnum]: Put[T] =
    Put[Int].contramap(_.code)
}
