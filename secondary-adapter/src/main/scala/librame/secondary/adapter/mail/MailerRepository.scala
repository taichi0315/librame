package librame.secondary.adapter.mail

import scala.concurrent.Future

import librame.domain.model.EmailAddress

trait MailerRepository {
  /**
   * @param to
   * @return
   */
  def send(to: EmailAddress): Future[Option[String]]
}