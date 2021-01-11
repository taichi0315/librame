package librame.domain.value.market

import java.lang.AssertionError

import scala.util.Try

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
    val result = for {
      m1     <- Money(3, Currency.JPY)
      m2     <- Money(2, Currency.USD)
      result <- Try(m1 + m2).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: UnsupportedOperationException => succeed
      case _                                => fail()
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
    val result = for {
      m1     <- Money(3)
      m2     <- Money(4)
      result <- Try(m1 - m2).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: AssertionError => succeed
      case _                 => fail()
    }
  }

  test("minus method exception") {
    val result = for {
      m1     <- Money(3, Currency.JPY)
      m2     <- Money(2, Currency.USD)
      result <- Try(m1 - m2).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: UnsupportedOperationException => succeed
      case _                                => fail()
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
    val result = for {
      m1     <- Money(3)
      result <- Try(m1 * -1).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: IllegalArgumentException => succeed
      case _                           => fail()
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
    val result = for {
      m1     <- Money(3)
      result <- Try(m1 / -1).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: IllegalArgumentException => succeed
      case _                           => fail()
    }
  }

  test("div method require 2") {
    val result = for {
      m1     <- Money(3)
      result <- Try(m1 / 0).toEither
    } yield result

    assert(result.isLeft)
    result.left.map {
      case _: IllegalArgumentException => succeed
      case _                           => fail()
    }
  }
}
