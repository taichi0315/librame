package librame.application.service

import scala.concurrent.{Future, ExecutionContext}

import org.atnos.eff._
import org.atnos.eff.either._
import org.atnos.eff.future._
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._
import org.atnos.eff.concurrent.Scheduler


trait EffService {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  type FutureStack = Fx.fx2[ServiceEither, TimedFuture]

  implicit class FutureOps[T](futureValue: Future[T])(implicit ec: ExecutionContext) {
    def toEff[R: _future]: Eff[R, T] = fromFuture(futureValue)
  }

  implicit class ServiceEitherOps[T](eitherValue: Either[error.ServiceErr, T])(implicit ec: ExecutionContext) {
    def toEff[R: _serviceEither]: Eff[R, T] = fromEither(eitherValue)
  }

  implicit class EffFutureStackOps[T](eff: Eff[FutureStack, T])(implicit ec: ExecutionContext) {
    def run: Future[Either[error.ServiceErr, T]] = eff.runEither.runAsync
  }
}

object EffService extends EffService
