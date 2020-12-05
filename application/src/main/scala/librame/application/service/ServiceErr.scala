package librame.application.service

package object error {
  
  sealed trait ServiceErr

  case class ValidateErr(msg: String)
  object ValidateErr {
    def apply(str: String) = new ValidateErr(s"invalid $str")
  }

  case class DuplicateErr(msg: String)
  object DuplicateErr {
    def apply(str: String) = new DuplicateErr(s"use already $str")
  }

  case class NotFoundErr(msg: String)
  object NotFoundErr {
    def apply(str: String) = new NotFoundErr(s"not found $str")
  }

  case class ServerErr(msg: String)
  object ServerErr {
    def apply() = new ServerErr("internal server error")
  }
}
