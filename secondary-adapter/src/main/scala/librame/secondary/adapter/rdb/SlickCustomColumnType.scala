package librame.secondary.adapter.rdb

import java.util.UUID

import scala.reflect.ClassTag

import slick.jdbc.{SetParameter, GetResult}

import librame.domain.model.SingleValueObject


trait SlickCustomColumnType extends SlickDatabaseConfig {
  import profile.api._

  implicit def valueStringMapped[T <: SingleValueObject[String]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value,
      str => tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    )

  implicit def valueUUIDMapped[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value.toString,
      str => tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    )

  implicit val valueStringSet = SetParameter[SingleValueObject[String]] {
    case (vo, params) => params.setString(vo.value)
  }

  implicit def valueStringGet[T <: SingleValueObject[String]](implicit tag: ClassTag[T]) =
    GetResult[SingleValueObject[String]] {
      result => tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(result.nextString)
        .asInstanceOf[T]
    }

  implicit val valueUUIDSet = SetParameter[SingleValueObject[UUID]] {
    case (vo, params) => params.setString(vo.value.toString)
  }

  implicit def valueUUIDGet[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]) =
    GetResult[SingleValueObject[UUID]] {
      result => tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(result.nextString))
        .asInstanceOf[T]
    }
}
