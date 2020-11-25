package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import org.atnos.eff._
import org.atnos.eff.Members.&&:

import EffService._

trait EffService[M[_]] {

  type _serviceEither[R] = ServiceEither |= R
  type _member[R]        = M |= R
}

object EffService {
  type ServiceEither[T] = Either[error.ServiceErr, T]
}
