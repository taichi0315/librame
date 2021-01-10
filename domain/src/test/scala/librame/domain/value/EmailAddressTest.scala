package librame.domain.value

import org.scalatest.FunSuite

class EmailAddressTest extends FunSuite {
  test("生成失敗") {
    assert(EmailAddress(".abcd@example.co.jp").isLeft)
    assert(EmailAddress("abcd.@example.co.jp").isLeft)
    assert(EmailAddress("abcd..@example.co.jp").isLeft)
    assert(EmailAddress("ab..cd@example.co.jp").isLeft)
    assert(EmailAddress("ab[cd@example.co.jp").isLeft)
    assert(EmailAddress("ab@cd@example.co.jp").isLeft)
  }

  test("生成成功") {
    assert(EmailAddress("k.taichi0315@gmail.com").isRight)
  }
}
