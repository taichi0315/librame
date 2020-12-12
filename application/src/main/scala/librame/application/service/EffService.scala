package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import cats.effect.IO
import org.atnos.eff._

trait EffService {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  implicit class FutureOps[T](future: Future[T]) {
    def toIO: IO[T] = IO.fromFuture(IO(future))
  }
}

object EffService extends EffService
