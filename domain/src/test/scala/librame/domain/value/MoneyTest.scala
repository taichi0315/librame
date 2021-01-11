package librame.domain.value

import scala.util.Try

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("正常") {
    assert(Money(0).isRight)
    assert(Money(1).isRight)
    assert(Money(100).isRight)
    assert(Money(0.1).isRight)
  }

  test("0より小さい") {
    assert(Money(-1).isLeft)
    assert(Money(-100).isLeft)
    assert(Money(-0.1).isLeft)
  }

  test("USD通貨") {
    assert(Money(1, "USD").isRight)
  }

  test("足し算") {
    for {
      m1     <- Money(3)
      m2     <- Money(2)
      except <- Money(5)
    } yield {
      assert(m1 + m2 == except)
      assert(m1.plus(m2) == except)
    }
  }
  
  test("引き算") {
    for {
      m1     <- Money(4)
      m2     <- Money(3)
      except <- Money(1)
    } yield {
      assert(m1 - m2 == except)
      assert(m1.minus(m2) == except)
    }
  }

  test("引き算失敗") {
    val result = for {
      m1     <- Money(3)
      m2     <- Money(4)
      result <- Try(m1.minus(m2)).toEither
    } yield result

    assert(result.isLeft)
  }

  test("掛け算") {
    for {
      m1     <- Money(3)
      except <- Money(9)
    } yield {
      assert(m1 * 3 == except)
      assert(m1.mul(3) == except)

    }
  }

  test("掛け算失敗") {
    val result = for {
      m1     <- Money(3)
      result <- Try(m1.mul(-1)).toEither
    } yield result

    assert(result.isLeft)
  }

  test("割り算") {
    for {
      m1     <- Money(9)
      except <- Money(3)
    } yield {
      assert(m1 / 3 == except)
      assert(m1.div(3) == except)
    }
  }

  test("割り算失敗①") {
    val result = for {
      m1     <- Money(3)
      result <- Try(m1.div(-1)).toEither
    } yield result

    assert(result.isLeft)
  }

  test("割り算失敗②") {
    val result = for {
      m1     <- Money(3)
      result <- Try(m1.div(0)).toEither
    } yield result

    assert(result.isLeft)
  }
}
