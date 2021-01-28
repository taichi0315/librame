package librame.domain.model.address

import org.scalatest.FunSuite

class AddressTest extends FunSuite {
  test("country code error") {
    assertResult(Left(Address.CountryCodeValidateErr)) {
      Address("error", "311-3107", "city", "address1", "address2")
    }
  }

  test("zip code error") {
    assertResult(Left(Address.ZipCodeValidateErr)) {
      Address("81", "error", "city", "address1", "address2")
    }
  }
}
