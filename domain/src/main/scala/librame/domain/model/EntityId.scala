package librame.domain.model

import java.util.UUID

import scala.reflect.ClassTag

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}

object EntityId {
  /**
   * @param tag
   * @tparam T
   * @return
   */
  def generate[T <: EntityId](implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.randomUUID)
      .asInstanceOf[T]

  /**
   * @param str
   * @param tag
   * @tparam T
   * @return
   */
  def fromString[T <: EntityId](str: String)(implicit tag: ClassTag[T]): T =
    tag.runtimeClass
      .getConstructor(classOf[UUID])
      .newInstance(UUID.fromString(str))
      .asInstanceOf[T]
}
