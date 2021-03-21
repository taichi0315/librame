package librame.domain.model

import org.scalatest.FunSuite

class SingleValueObjectTest extends FunSuite {
  
  case class A(value: String) extends SingleValueObject[String]

  test("String: 同じ値を持っている場合等しい") {
    assertResult(A("test"))(A("test"))
  }

  test("String: 違う値を持っている場合等しくない") {
    val a1 = A("test1")
    val a2 = A("test2")

    assert(a1 != a2)
  }

  case class B(value: Int) extends SingleValueObject[Int]

  test("Int: 同じ値を持っている場合等しい") {
    assertResult(B(1))(B(1))
  }

  test("Int: 違う値を持っている場合等しくない") {
    val a1 = B(1)
    val a2 = B(2)

    assert(a1 != a2)
  }
}
