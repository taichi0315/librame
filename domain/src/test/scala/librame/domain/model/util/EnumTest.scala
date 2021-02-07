package librame.domain.model.util

import org.scalatest.FunSuite

class EnumTest extends FunSuite {

  sealed abstract class IntStatus(val code: Int) extends EnumInt
  object IntStatus extends EnumInt.Of[IntStatus] {
    case object TODO        extends IntStatus(code = 0)
    case object IN_PROGRESS extends IntStatus(code = 1)
    case object DONE        extends IntStatus(code = 2)
    
    val values: Seq[IntStatus] = Seq(TODO, IN_PROGRESS, DONE)
  }
  
  test("EnumInt: get instance") {
    import IntStatus._

    assertResult(TODO)       (IntStatus(0))
    assertResult(IN_PROGRESS)(IntStatus(1))
    assertResult(DONE)       (IntStatus(2))
  }

  test("EnumInt: not found code") {
    assertThrows[java.util.NoSuchElementException](IntStatus(-1))
    assertThrows[java.util.NoSuchElementException](IntStatus(3))
  } 

  sealed abstract class StringStatus(val code: String) extends EnumString
  object StringStatus extends EnumString.Of[StringStatus] {
    case object TODO        extends StringStatus(code = "0")
    case object IN_PROGRESS extends StringStatus(code = "1")
    case object DONE        extends StringStatus(code = "2")
    
    val values: Seq[StringStatus] = Seq(TODO, IN_PROGRESS, DONE)
  }

  
  test("EnumString: get instance") {
    import StringStatus._

    assertResult(TODO)       (StringStatus("0"))
    assertResult(IN_PROGRESS)(StringStatus("1"))
    assertResult(DONE)       (StringStatus("2"))
  }

  test("EnumString: not found code") {
    assertThrows[java.util.NoSuchElementException](StringStatus("failed"))
  }
} 
