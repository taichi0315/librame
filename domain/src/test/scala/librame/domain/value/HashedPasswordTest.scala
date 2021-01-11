package librame.domain.value

import org.scalatest.FunSuite

class HasedPasswordTest extends FunSuite {

  test("生成：正常") {
    assert(HashedPassword("abcdefgh").isRight)
    assert(HashedPassword("abcdefgh0315").isRight)
  }

  test("生成：文字数不足") {
    assert(HashedPassword("abcd").isLeft)
    assert(HashedPassword("abcdefg").isLeft)
  }

  test("生成：半角英数記号以外") {
    assert(HashedPassword("abcdefあ").isLeft)
  }
}
