package librame.domain.value

import org.scalatest.FunSuite

import librame.domain.error._

class EmailAddressTest extends FunSuite {

  test("失敗") {

    assert(EmailAddress("abcd") == Left(EmailAddressValidation))
  }
}
