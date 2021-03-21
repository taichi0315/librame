package librame.domain.model.address

import org.scalatest.FunSuite

class ZipCodeTest extends FunSuite {
  test("success") {
    assert(ZipCode("311-3107").isRight)
  }

  test("fail") {
    assert(ZipCode("").isLeft)
    assert(ZipCode("31-1111").isLeft)
    assert(ZipCode("テスト").isLeft)
  }
}
