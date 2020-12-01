package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import org.atnos.eff._
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.{either, future}
import doobie._

class EffService()(implicit val ec: ExecutionContext) extends either with future {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  type FutureStack       = Fx.fx2[ServiceEither, TimedFuture]
  type ConnectionIOStack = Fx.fx2[ServiceEither, ConnectionIO]

  implicit class FutureStackOps[T](future: Eff[FutureStack, T]) {
    def run: Future[ServiceEither[T]] =
      future.runEither.runAsync
  }
}
