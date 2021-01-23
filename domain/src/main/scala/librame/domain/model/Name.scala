package librame.domain.model

/**
 * Name Value Object
 * @param lastName
 * @param firstName
 */
case class Name(
  lastName:  String,
  firstName: String
) {
  lazy val fullName: String = s"$lastName $firstName"
}