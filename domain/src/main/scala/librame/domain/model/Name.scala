package librame.domain.model

/**
 * @param lastName
 * @param firstName
 */
case class Name(
  lastName:  String,
  firstName: String
) {
  lazy val fullName: String = s"$lastName $firstName"
}