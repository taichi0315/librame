package ddd.lib.adapter.secondary.rdb

import java.util.UUID

import scala.reflect.ClassTag

import com.mohiva.play.silhouette.api.util.PasswordInfo

import ddd.lib.domain.value.Value


trait SlickCustomColumnType extends SlickDatabaseConfig {
  import profile.api._

  implicit def ValueStringType[T <: Value[String]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value,
      str => tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    )

  implicit def ValueUUIDType[T <: Value[UUID]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value.toString,
      str => tag.runtimeClass
        .getConstructor(classOf[UUID])
        .newInstance(UUID.fromString(str))
        .asInstanceOf[T]
    )

  implicit def ValuePasswordInfoType[T <: Value[PasswordInfo]](implicit tag: ClassTag[T]) =
    MappedColumnType.base[T, String](
      vo  => vo.value.password,
      str => tag.runtimeClass
        .getConstructor(classOf[PasswordInfo])
        .newInstance(new PasswordInfo("bcrypt", str))
        .asInstanceOf[T]
    )

}
