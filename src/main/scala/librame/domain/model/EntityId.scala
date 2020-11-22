package ddd.lib.domain.model

import java.util.UUID

import ddd.lib.domain.value.Value

trait EntityId extends Value[UUID] {
  val value: UUID
}
