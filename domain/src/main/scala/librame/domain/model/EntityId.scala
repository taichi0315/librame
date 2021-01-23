package librame.domain.model

import java.util.UUID

/**
 * EntityId Value Object
 */
trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}
