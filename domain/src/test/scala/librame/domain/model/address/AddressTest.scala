package librame.domain.model.address

import org.scalatest.FunSuite

class AddressTest extends FunSuite {
  test("country code error") {
    val result = Address("error", "311-3107", "city", "address1", "address2")
    val except = Left(Address.CountryCodeValidateErr)

    assert(result == except)
  }

  test("zip code error") {
    val result = Address("81", "error", "city", "address1", "address2")
    val except = Left(Address.ZipCodeValidateErr)

    assert(result == except)
  }
}
