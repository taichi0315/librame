package ddd.lib.adapter.secondary.mail

import scala.concurrent.Future

import ddd.lib.domain.value.EmailAddress

trait MailerRepository {
  def send(to: EmailAddress): Future[Option[String]]
}
