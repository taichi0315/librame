package librame.adapter.secondary.mail

import scala.concurrent.Future

import librame.domain.value.EmailAddress

trait MailerRepository {
  def send(to: EmailAddress): Future[Option[String]]
}
