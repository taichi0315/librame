package librame.domain.model

case class Name(
  lastName:  String,
  firstName: String
) {
  lazy val fullName: String = s"$lastName $firstName"
}
