package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import org.atnos.eff._
import org.atnos.eff.either._
import org.atnos.eff.future._
import org.atnos.eff.syntax.{either, future}
import org.atnos.eff.concurrent.Scheduler


trait EffService extends either with future {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  type FutureStack = Fx.fx2[ServiceEither, TimedFuture]
}

object EffService extends EffService
