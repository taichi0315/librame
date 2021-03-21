package librame.domain.model.person

import org.scalatest.FunSuite

class NameTest extends FunSuite {
  test("fullName value") {
    assertResult("山田 太郎")(Name("山田", "太郎").fullName)
    assertResult("Yamada Taro")(Name("Yamada", "Taro").fullName)
    assertResult("YAMADA Taro")(Name("YAMADA", "Taro").fullName)
  }
}
