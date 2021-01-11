package librame.domain.value.market

import org.scalatest.FunSuite

class PriceTest extends FunSuite {

  test("calculate 100") {
    for {
      m      <- Money(100)
      except <- Money(10)
    } yield
      assert(Price(m).tax == except)
  }

  test("calculate 2100") {
    for {
      m      <- Money(2100)
      except <- Money(210)
    } yield
      assert(Price(m).tax == except)
  }
}
