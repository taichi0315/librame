package librame.domain.value

import org.scalatest.FunSuite

class CurrencyTest extends FunSuite {

  test("正常") {
    assert(Currency("JPY").isRight)
    assert(Currency("USD").isRight)
    assert(Currency("EUR").isRight)
    assert(Currency("GBP").isRight)
  }

  test("文字数") {
    assert(Currency("AB").isLeft)
    assert(Currency("ABCD").isLeft)
    assert(Currency("AAAAAAAAAAAAAAA").isLeft)
  }

  test("大文字") {
    assert(Currency("jpy").isLeft)
    assert(Currency("jpY").isLeft)
  }
}
