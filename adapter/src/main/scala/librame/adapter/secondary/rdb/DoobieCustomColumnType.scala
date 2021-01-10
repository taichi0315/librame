package librame.adapter.secondary.rdb

import java.util.UUID

import scala.reflect.ClassTag

import com.mohiva.play.silhouette.api.util.PasswordInfo
import doobie.util._

import librame.domain.value._

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

  implicit def valuePasswordInfoGet[T <: SingleValueObject[PasswordInfo]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[PasswordInfo])
        .newInstance(new PasswordInfo("bcrypt", str))
        .asInstanceOf[T]
    }
  implicit def valuePasswordInfoGetPut[T <: SingleValueObject[PasswordInfo]]: Put[T] =
    Put[String].contramap(_.value.password)
}
