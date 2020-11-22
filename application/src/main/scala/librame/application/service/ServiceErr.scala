package librame.application.service

package object error {
  
  sealed trait ServiceErr { val msg: String }

  case class ValidateErr(msg: String) extends ServiceErr
  object ValidateErr {
    def apply(str: String) = new ValidateErr(msg = s"invalid $str")
  }

  case class DuplicateErr(msg: String) extends ServiceErr
  object DuplicateErr {
    def apply(str: String) = new DuplicateErr(msg = s"use already $str")
  }

  case class NotFoundErr(msg: String) extends ServiceErr
  object NotFoundErr {
    def apply(str: String) = new NotFoundErr(msg = s"not found $str")
  }


  case class MailerErr(msg: String) extends ServiceErr
  object MailerErr {
    def apply() = new NotFoundErr(msg = "not send mail")
  }
}
