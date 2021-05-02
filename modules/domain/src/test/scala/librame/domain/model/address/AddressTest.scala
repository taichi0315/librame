package librame.domain.model.address

import org.scalatest.FunSuite

class AddressTest extends FunSuite {
  test("country code error") {
    assertResult(Left(Address.CountryCodeValidateValidation)) {
      Address("error", "311-3107", "city", "address1", "address2")
    }
  }

  test("zip code error") {
    assertResult(Left(Address.ZipCodeValidateValidation)) {
      Address("81", "error", "city", "address1", "address2")
    }
  }
}
