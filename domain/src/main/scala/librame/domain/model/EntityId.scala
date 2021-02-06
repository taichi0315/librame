package librame.domain.model

import java.util.UUID

import scala.reflect.ClassTag

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}

object EntityId {
  def generate[T <: EntityId](implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.randomUUID)
      .asInstanceOf[T]

  def fromString[T <: EntityId](str: String)(implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[T]
}
