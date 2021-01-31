package librame.domain.model

import java.util.UUID

import scala.reflect.ClassTag

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}

object EntityId {
  def generate[I <: EntityId : ClassTag]: I =
    implicitly[ClassTag[I]]
      .runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.randomUUID)
      .asInstanceOf[I]

  def fromString[I <: EntityId : ClassTag](str: String): I =
    implicitly[ClassTag[I]]
      .runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[I]
}
