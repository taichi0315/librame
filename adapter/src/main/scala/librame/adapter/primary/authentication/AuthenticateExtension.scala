package librame.adapter.primary.authentication

import scala.concurrent.{Future, ExecutionContext}

import play.api.mvc._

import librame.domain.model.EntityId

/**
 * @tparam T
 */
trait AuthenticateExtension[T <: EntityId] {self: BaseControllerHelpers =>
  
  import ExecutionContext.Implicits.global

  /**
   * @param authProfile
   * @return
   */
  def AuthenticateAction(authProfile: AuthenticationProfile[T]): ActionBuilder[Request, AnyContent] =
    AuthenticateActionBuilder[T](authProfile, parse.default)
}

/**
 * @param auth
 * @param parse
 * @param ec
 * @tparam T
 */
case class AuthenticateActionBuilder[T <: EntityId](
  auth:  AuthenticationProfile[T],
  parse: BodyParser[AnyContent]
) (implicit ec: ExecutionContext)
extends ActionBuilderImpl(parse) {

  /**
   * @param request
   * @param block
   * @tparam A
   * @return
   */
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) =
    auth.authenticate(request).flatMap {
      case Left(l)          => Future.successful(l)
      case Right((entityId, put)) => block {
        request.addAttr(auth.AttrKeys.Auth, entityId)
      }.map(put)
    }
}
