package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import cats.effect.IO
import org.atnos.eff._
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.addon.cats.effect._
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._

class EffService()(implicit ec: ExecutionContext) {

  implicit val cs = IO.contextShift(ExecutionContext.global)
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  type FutureStack = Fx.fx2[ServiceEither, TimedFuture]
  type IOStack     = Fx.fx2[ServiceEither, IO]

  implicit class FutureOps[T](future: Future[T]) {
    def toIO: IO[T] = IO.fromFuture(IO(future))
  }

  implicit class FutureStackOps[T](future: Eff[FutureStack, T]) {
    def run: Future[ServiceEither[T]] =
      future.runEither.runAsync
  }

  implicit class IOStackOps[T](io: Eff[IOStack, T]) {
    def run: Future[ServiceEither[T]] =
      io.runEither.unsafeToFuture
  }
}
