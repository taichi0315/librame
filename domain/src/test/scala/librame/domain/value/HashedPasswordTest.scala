package librame.domain.value

import org.scalatest.FunSuite

class HasedPasswordTest extends FunSuite {

  test("正常") {
    assert(HashedPassword("absdefgh").isRight)
  }

  test("文字数不足") {
    assert(HashedPassword("abcdefg").isLeft)
  }
}
