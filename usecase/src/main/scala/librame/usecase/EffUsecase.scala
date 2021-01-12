package librame.usecase

import scala.concurrent.{Future, ExecutionContext}

import cats.effect.IO
import org.atnos.eff._

trait EffUseCase {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  type UseCaseEither[T]  = Either[error.UseCaseErr, T]
  type _usecaseEither[R] = UseCaseEither |= R

  implicit class FutureOps[T](future: Future[T]) {
    def toIO: IO[T] = IO.fromFuture(IO(future))
  }
}

object EffUseCase extends EffUseCase
