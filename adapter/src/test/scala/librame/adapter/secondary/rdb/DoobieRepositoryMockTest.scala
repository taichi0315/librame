package librame.adapter.secondary.rdb

import org.scalatest.FunSuite

import doobie.implicits._

class DoobieRepositoryMockTest extends FunSuite {
  
  test("test") {
    val repo = new DoobieRepositoryMock {}

    val result = repo.get.transact(repo.xa).unsafeRunSync

    assert(result == 0)
  }
}
