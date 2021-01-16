package librame.domain.model

import java.util.UUID

import librame.domain.model.SingleValueObject

trait EntityId extends SingleValueObject[UUID] {
  val value: UUID
}
