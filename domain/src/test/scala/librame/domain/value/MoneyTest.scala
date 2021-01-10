package librame.domain.value

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("生成失敗") {
    assert(Money(0).isLeft)
    assert(Money(-1).isLeft)
    assert(Money(-100).isLeft)
    assert(Money(-0.1).isLeft)
  }

  test("生成成功") {
    assert(Money(1).isRight)
    assert(Money(100).isRight)
    assert(Money(0.1).isRight)
    assert(Money(1, "USD").isRight)
  }
}
