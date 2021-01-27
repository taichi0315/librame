package librame.domain.model

import org.scalatest.FunSuite

import scala.util.Try

class QuantityTest extends FunSuite {

  test("生成：正常") {
    assert(Quantity(0).isRight)
    assert(Quantity(1).isRight)
    assert(Quantity(100).isRight)
  }

  test("生成：0より小さい") {
    assert(Quantity(-1).isLeft)
    assert(Quantity(-100).isLeft)
  }

  test("不変条件") {
    assert(Try(new Quantity(-1)).toEither.isLeft)
  }
}
