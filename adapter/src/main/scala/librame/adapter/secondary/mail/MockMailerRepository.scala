package librame.adapter.secondary.mail

import java.util.UUID

import scala.concurrent.Future

import librame.domain.model.EmailAddress

class MockMailerRepository extends MailerRepository {
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
