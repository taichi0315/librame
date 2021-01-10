package librame.domain.value

import org.scalatest.FunSuite

class CurrencyTest extends FunSuite {

  test("失敗") {
    assert(Currency("AB").isLeft)
    assert(Currency("ABCD").isLeft)
    assert(Currency("AAAAAAAAAAAAAAA").isLeft)
    assert(Currency("jpy").isLeft)
    assert(Currency("jpY").isLeft)
  }

  test("成功") {
    assert(Currency("JPY").isRight)
    assert(Currency("USD").isRight)
    assert(Currency("EUR").isRight)
    assert(Currency("GBP").isRight)
  }
}
