package librame.domain.value

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("正常") {
    assert(Money(1).isRight)
    assert(Money(100).isRight)
    assert(Money(0.1).isRight)
  }

  test("0より小さい") {
    assert(Money(0).isLeft)
    assert(Money(-1).isLeft)
    assert(Money(-100).isLeft)
    assert(Money(-0.1).isLeft)
  }

  test("USD通貨") {
    assert(Money(1, "USD").isRight)
  }
}
