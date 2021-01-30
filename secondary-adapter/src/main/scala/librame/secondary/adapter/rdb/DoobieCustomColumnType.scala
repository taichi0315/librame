package librame.secondary.adapter.rdb

import java.util.UUID

import scala.reflect.ClassTag

import doobie.util._

import librame.domain.model._

trait DoobieCustomColumnType {

  implicit def valueStringGet[T <: SingleValueObject[String]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    }
  implicit def valueStringPut[T <: SingleValueObject[String]]: Put[T] =
    Put[String].contramap(_.value)

  implicit def valueUUIDGet[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    }
  implicit def valueUUIDPut[T <: SingleValueObject[UUID]]: Put[T] =
    Put[String].contramap(_.value.toString)
}