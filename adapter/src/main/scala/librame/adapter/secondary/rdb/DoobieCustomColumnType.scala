package librame.adapter.secondary.rdb

import scala.reflect.ClassTag

import doobie.util._

import librame.domain.value._

trait DoobieCustomColumnType {

  implicit def valueStringGet[T <: Value[String]](implicit tag: ClassTag[T]): Get[T] =
    Get[String].map { str =>
      tag.runtimeClass
        .getConstructor(classOf[String])
        .newInstance(str)
        .asInstanceOf[T]
    }

  implicit def valueStringPut[T <: Value[String]]: Put[T] =
    Put[String].contramap(_.value)
}
