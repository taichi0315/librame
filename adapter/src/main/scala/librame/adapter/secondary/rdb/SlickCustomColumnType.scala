package librame.adapter.secondary.rdb

import java.util.UUID

import scala.reflect.ClassTag

import librame.domain.model.SingleValueObject


trait SlickCustomColumnType extends SlickDatabaseConfig {
  import profile.api._

  implicit def SingleValueObjectStringType[T <: SingleValueObject[String]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value,
      str => tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    )

  implicit def SingleValueObjectUUIDType[T <: SingleValueObject[UUID]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value.toString,
      str => tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    )
}
