package ddd.lib.adapter.primary.auth

import scala.concurrent.{Future, ExecutionContext}

import play.api.mvc._

import ddd.lib.domain.model.EntityId

trait AuthExtension[T <: EntityId] {self: BaseControllerHelpers =>
  
  import ExecutionContext.Implicits.global


  def AuthAction(authProfile: AuthProfile[T]): ActionBuilder[Request, AnyContent] =
    AuthActionBuilder[T](authProfile, parse.default)
}

case class AuthActionBuilder[T <: EntityId](
  auth:  AuthProfile[T],
  parse: BodyParser[AnyContent]
) (implicit ec: ExecutionContext)
extends ActionBuilderImpl(parse) {
  
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) =
    auth.authenticate(request).flatMap {
      case Left(l)          => Future.successful(l)
      case Right((entityId, put)) => block {
        request.addAttr(auth.AttrKeys.Auth, entityId)
      }.map(put)
    }
}
