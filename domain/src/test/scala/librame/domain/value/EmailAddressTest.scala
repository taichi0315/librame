package librame.domain.value

import org.scalatest.FunSuite

class EmailAddressTest extends FunSuite {
  test("正常") {
    assert(EmailAddress("k.taichi0315@gmail.com").isRight)
  }

  test("メールアドレスが準拠しない") {
    assert(EmailAddress(".abcd@example.co.jp").isLeft)
    assert(EmailAddress("abcd.@example.co.jp").isLeft)
    assert(EmailAddress("abcd..@example.co.jp").isLeft)
    assert(EmailAddress("ab..cd@example.co.jp").isLeft)
    assert(EmailAddress("ab[cd@example.co.jp").isLeft)
    assert(EmailAddress("ab@cd@example.co.jp").isLeft)
  }

}
