package librame.domain.model

import java.util.UUID
import org.scalatest.FunSuite

class EntityIdTest extends FunSuite {
  
  test("違う型の場合等しくない") {
    case class EntityA(id: EntityA.Id)
    object EntityA { case class Id(value: UUID) extends EntityId }

    case class EntityB(id: EntityB.Id)
    object EntityB { case class Id(value: UUID) extends EntityId }

    val entityA: EntityA =
      EntityA(id = EntityA.Id(UUID.randomUUID))

    val entityB: EntityB =
      EntityB(id = EntityB.Id(UUID.randomUUID))

    assert(entityA.id != entityB.id)
  }

  test("同じ型でIdの値が等しくない") {
    case class EntityA(id: EntityA.Id)
    object EntityA { case class Id(value: UUID) extends EntityId }

    val entity1: EntityA =
      EntityA(id = EntityA.Id(UUID.randomUUID))

    val entity2: EntityA =
      EntityA(id = EntityA.Id(UUID.randomUUID))

    assert(entity1.id != entity2.id)
  }

  test("同じ型でIdの値が等しい") {
    case class EntityA(id: EntityA.Id)
    object EntityA { case class Id(value: UUID) extends EntityId }

    val entity1: EntityA =
      EntityA(id = EntityA.Id(UUID.fromString("698176dc-4028-4638-a452-f00bf62a7781")))

    val entity2: EntityA =
      EntityA(id = EntityA.Id(UUID.fromString("698176dc-4028-4638-a452-f00bf62a7781")))

    assert(entity1.id == entity2.id)
  }
}
