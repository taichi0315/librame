package librame.domain.model

import org.scalatest.FunSuite

class NameTest extends FunSuite {
  test("fullName") {
    assert(Name("山田", "太郎").fullName == "山田 太郎")
    assert(Name("Yamada", "Taro").fullName == "Yamada Taro")
    assert(Name("YAMADA", "Taro").fullName == "YAMADA Taro")
  }
}
