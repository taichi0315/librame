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
