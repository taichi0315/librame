package librame.domain.value

import java.util.UUID

import librame.domain.value.SingleValueObject

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}
