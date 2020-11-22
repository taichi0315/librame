package librame.domain.model

import java.util.UUID

import librame.domain.value.Value

trait EntityId extends Value[UUID] {
  val value: UUID
}
