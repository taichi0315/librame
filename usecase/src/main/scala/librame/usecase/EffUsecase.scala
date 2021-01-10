package librame.usecase

import scala.concurrent.{Future, ExecutionContext}

import cats.effect.IO
import org.atnos.eff._

trait EffUsecase {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  type UsecaseEither[T]  = Either[error.UsecaseErr, T]
  type _usecaseEither[R] = UsecaseEither |= R

  implicit class FutureOps[T](future: Future[T]) {
    def toIO: IO[T] = IO.fromFuture(IO(future))
  }
}

object EffUsecase extends EffUsecase
