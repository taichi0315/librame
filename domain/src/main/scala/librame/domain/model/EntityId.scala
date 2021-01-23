package librame.domain.model

import java.util.UUID

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}