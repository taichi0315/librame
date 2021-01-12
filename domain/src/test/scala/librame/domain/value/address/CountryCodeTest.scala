package librame.domain.value.address

import org.scalatest.FunSuite

class CountryCodeTest extends FunSuite {
  test("success") {
    assert(CountryCode("81").isRight)    // Japan
    assert(CountryCode("1").isRight)     // United States
    assert(CountryCode("1-284").isRight) // British Virgin Islands
  }

  test("fail") {
    assert(CountryCode("+81").isLeft)
    assert(CountryCode("テスト").isLeft)
    assert(CountryCode("").isLeft)
  }
}
