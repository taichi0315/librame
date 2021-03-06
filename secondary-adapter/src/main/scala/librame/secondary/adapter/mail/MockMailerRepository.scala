package librame.secondary.adapter.mail

import java.util.UUID

import scala.concurrent.Future

import librame.domain.model.EmailAddress

class MockMailerRepository extends MailerRepository {

  /** @param to
    * @return
    */
  def send(to: EmailAddress): Future[Option[String]] = {
    val token = UUID.randomUUID.toString

    Future.successful(
      token.isEmpty match {
        case true  => None
        case false => Some(token)
      }
    )
  }
}
