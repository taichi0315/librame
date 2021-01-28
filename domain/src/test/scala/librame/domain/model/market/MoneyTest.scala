package librame.domain.model.market

import java.lang.AssertionError

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("生成：正常") {
    assert(Money(0).isRight)
    assert(Money(1).isRight)
    assert(Money(100).isRight)
    assert(Money(0.1).isRight)
  }

  test("生成：0より小さい") {
    assert(Money(-1).isLeft)
    assert(Money(-100).isLeft)
    assert(Money(-0.1).isLeft)
  }

  test("不変条件") {
    assertThrows[AssertionError](new Money(-1,   Currency.JPY))
    assertThrows[AssertionError](new Money(-100, Currency.JPY))
  }

  test("plus method success") {
    for {
      m1     <- Money(3)
      m2     <- Money(2)
      except <- Money(5)
    } yield {
      assert(m1 + m2 == except)
      assert(m1.plus(m2) == except)
    }
  }

  test("plus method exception") {
    assertThrows[UnsupportedOperationException] {
      for {
        m1 <- Money(3, Currency.JPY)
        m2 <- Money(2, Currency.USD)
      } yield m1 + m2
    }
  }
  
  test("minus method success") {
    for {
      m1     <- Money(4)
      m2     <- Money(3)
      except <- Money(1)
    } yield {
      assert(m1 - m2 == except)
      assert(m1.minus(m2) == except)
    }
  }

  test("minus method ensuring") {
    assertThrows[AssertionError] {
      for {
        m1 <- Money(3)
        m2 <- Money(4)
      } yield m1 - m2
    }
  }

  test("minus method exception") {
    assertThrows[UnsupportedOperationException] {
      for {
        m1 <- Money(3, Currency.JPY)
        m2 <- Money(2, Currency.USD)
      } yield m1 - m2
    }
  }

  test("mul method success") {
    for {
      m1     <- Money(3)
      except <- Money(9)
    } yield {
      assert(m1 * 3 == except)
      assert(m1.mul(3) == except)
    }
  }

  test("mul method require") {
    assertThrows[IllegalArgumentException] {
      for {
        m1 <- Money(3)
      } yield m1 * -1
    }
  }

  test("div method success") {
    for {
      m1     <- Money(9)
      except <- Money(3)
    } yield {
      assert(m1 / 3 == except)
      assert(m1.div(3) == except)
    }
  }

  test("div method require 1") {
    assertThrows[IllegalArgumentException] {
      for {
        m1 <- Money(3)
      } yield m1 / -1
    }
  }

  test("div method require 2") {
    assertThrows[IllegalArgumentException] {
      for {
        m1 <- Money(3)
      } yield m1 / 0
    }
  }
}
