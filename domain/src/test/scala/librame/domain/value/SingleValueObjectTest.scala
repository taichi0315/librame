package librame.domain.value

import org.scalatest.FunSuite

class SingleValueObjectTest extends FunSuite {
  
  test("同じ型で同じ値を持っている場合等しい") {
    case class A(value: String) extends SingleValueObject[String]

    val a1 = A("test")
    val a2 = A("test")

    assert(a1 == a2)
  }

  test("同じ型で違う値を持っている場合等しくない") {
    case class A(value: String) extends SingleValueObject[String]

    val a1 = A("test1")
    val a2 = A("test2")

    assert(a1 != a2)
  }
}
