package librame.domain.value.market

import org.scalatest.FunSuite

class SalesTaxTest extends FunSuite {

  test("calculate 100") {
    for {
      m      <- Money(100)
      except <- Money(10)
    } yield
      assert(SalesTax(m).calculate == except)
  }

  test("calculate 2100") {
    for {
      m      <- Money(2100)
      except <- Money(210)
    } yield
      assert(SalesTax(m).calculate == except)
  }
}
