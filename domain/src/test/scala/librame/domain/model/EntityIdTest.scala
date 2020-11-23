package librame.domain.model

import java.util.UUID
import org.scalatest.FunSuite

class EntityIdTest extends FunSuite {
  
  test("EntityIdの等価性を確認") {
    case class EntityA(id: EntityA.Id) extends Entity
    object EntityA { case class Id(value: UUID) extends EntityId }

    case class EntityB(id: EntityB.Id) extends Entity
    object EntityB { case class Id(value: UUID) extends EntityId }

    val entityA: EntityA =
      EntityA(id = EntityA.Id(UUID.randomUUID))

    val entityB: EntityB =
      EntityB(id = EntityB.Id(UUID.randomUUID))

    assert(entityA.id != entityB.id)
  }
}
